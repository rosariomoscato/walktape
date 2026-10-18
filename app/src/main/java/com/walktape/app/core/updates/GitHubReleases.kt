package com.walktape.app.core.updates

import com.google.gson.JsonParser
import com.walktape.app.core.net.NetworkTraffic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * W11-T04: read-only access to GitHub Releases for rosariomoscato/walktape. Uses its
 * own OkHttpClient (never the authenticated Navidrome client), unauthenticated, with
 * the standard GitHub headers; redirects are enabled because asset downloads are
 * served from objects.githubusercontent.com. No tokens are embedded or stored.
 */
class GitHubReleases(
    private val http: OkHttpClient = defaultClient(),
    private val baseUrl: String = "https://api.github.com",
) {

    sealed interface Result {
        /** The latest published full release (drafts/prereleases are excluded by GitHub). */
        data class Latest(val release: ReleaseInfo) : Result
        /** 404: no published release yet — an honest state, not an error. */
        data object NoReleases : Result
        data class RateLimited(val retryAfterSec: Long?) : Result
        data object Network : Result
        data object Invalid : Result
    }

    suspend fun latest(owner: String, repo: String): Result = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$baseUrl/repos/$owner/$repo/releases/latest")
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .header("User-Agent", USER_AGENT)
            .build()
        val response = try {
            http.newCall(request).execute()
        } catch (_: IOException) {
            return@withContext Result.Network
        }
        response.use {
            when {
                it.isSuccessful -> parse(it.body.string())
                it.code == 404 -> Result.NoReleases
                it.code == 403 || it.code == 429 -> {
                    val remaining = it.header("X-RateLimit-Remaining")
                    val retryAfter = it.header("Retry-After")?.toLongOrNull()
                    if (remaining == "0" || retryAfter != null) Result.RateLimited(retryAfter)
                    else Result.Network
                }
                else -> Result.Invalid
            }
        }
    }

    /**
     * Downloads a small text asset (e.g. SHA256SUMS.txt) over the same HTTPS-only
     * client. Body is capped; returns null on any transport or size failure.
     */
    suspend fun downloadText(url: String, maxBytes: Int = MAX_TEXT_ASSET_BYTES): String? =
        withContext(Dispatchers.IO) {
            if (!url.startsWith("https://")) return@withContext null
            val request = Request.Builder().url(url)
                .header("User-Agent", USER_AGENT)
                .build()
            val response = try {
                http.newCall(request).execute()
            } catch (_: IOException) {
                return@withContext null
            }
            response.use {
                if (!it.isSuccessful) return@withContext null
                val body = it.body ?: return@withContext null
                try {
                    val bytes = body.bytes()
                    if (bytes.size > maxBytes) null else String(bytes, Charsets.UTF_8)
                } catch (_: IOException) { null }
            }
        }

    private fun parse(body: String?): Result {
        if (body == null) return Result.Invalid
        return try {
            val root = JsonParser.parseString(body).asJsonObject
            val assets = root.getAsJsonArray("assets")?.map { element ->
                val asset = element.asJsonObject
                ReleaseAsset(
                    name = asset.get("name")?.asString ?: return Result.Invalid,
                    url = asset.get("browser_download_url")?.asString ?: return Result.Invalid,
                    sizeBytes = asset.get("size")?.takeIf { it.isJsonPrimitive }?.asLong ?: 0L,
                    contentType = asset.get("content_type")?.takeIf { it.isJsonPrimitive }?.asString ?: "",
                )
            }.orEmpty()
            val tagName = root.get("tag_name")?.takeIf { it.isJsonPrimitive }?.asString
                ?: return Result.Invalid
            Result.Latest(ReleaseInfo(
                tagName = tagName,
                name = root.get("name")?.takeIf { it.isJsonPrimitive }?.asString,
                body = root.get("body")?.takeIf { it.isJsonPrimitive }?.asString,
                publishedAt = root.get("published_at")?.takeIf { it.isJsonPrimitive }?.asString,
                assets = assets,
            ))
        } catch (_: Exception) {
            Result.Invalid
        }
    }

    companion object {
        const val USER_AGENT = "WalkTape-Update"
        const val MAX_TEXT_ASSET_BYTES = 64 * 1024

        /**
         * Dedicated client: redirects on (asset downloads redirect within GitHub),
         * sane timeouts, global offline listener attached. A network interceptor
         * enforces HTTPS on every hop, including redirect follow-ups: the updater
         * must never fetch over cleartext even if an API response or redirect
         * points at an http:// URL.
         */
        fun defaultClient(): OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .eventListener(NetworkTraffic.listener)
            .addNetworkInterceptor { chain ->
                val request = chain.request()
                if (!request.url.isHttps) {
                    throw IOException("Update transport must be HTTPS: ${request.url.scheme}:// refused")
                }
                chain.proceed(request)
            }
            .build()
    }
}
