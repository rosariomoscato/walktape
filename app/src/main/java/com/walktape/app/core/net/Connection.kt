package com.walktape.app.core.net

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.security.MessageDigest
import java.security.SecureRandom

/** Explicit HTTP opt-in belongs to the profile, never inferred from a hostname. */
class ServerAddress private constructor(val url: HttpUrl) {
    val isCleartext: Boolean get() = url.scheme == "http"
    override fun toString() = "ServerAddress([redacted])"

    companion object {
        fun parse(value: String, allowLocalHttp: Boolean = false): ServerAddress? {
            val url = value.trim().toHttpUrlOrNull() ?: return null
            if (url.username.isNotEmpty() || url.password.isNotEmpty() ||
                url.query != null || url.fragment != null ||
                (url.scheme == "http" && !allowLocalHttp)) return null
            return ServerAddress(url.newBuilder().apply {
                if (!url.encodedPath.endsWith('/')) addPathSegment("")
            }.build())
        }
    }
}

/**
 * Never a data class: generated toString/copy must not expose credentials.
 * [usesApiKey] selects the OpenSubsonic apiKeyAuthentication mode: [password] then holds
 * the server-issued API key, which is sent as the sole `apiKey` query parameter (never
 * together with `u`/`t`/`s`, per extension spec — the server answers error 43 otherwise).
 * A captured key is revocable on the server and is not derived from the user password.
 */
class Credentials(val username: String, private val password: String, val usesApiKey: Boolean = false) {
    internal fun token(salt: String): String = MessageDigest.getInstance("MD5")
        .digest((password + salt).toByteArray(Charsets.UTF_8)).toHex()

    /** The raw secret for apiKey mode; never logged, only placed in the request URL. */
    internal fun apiKey(): String = password
    override fun toString() = "Credentials([redacted])"
}

internal fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it.toInt() and 0xff) }

/** A fresh salt per request is a project decision (D17): never reuse one across calls. */
fun randomSalt(): String = ByteArray(16).also { SecureRandom().nextBytes(it) }.toHex()
