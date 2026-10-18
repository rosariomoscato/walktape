package com.walktape.app.core.updates

import java.io.File
import java.security.MessageDigest

/**
 * Pure parsing/hash helpers for sha256sum-style checksum files
 * (`<64 hex chars>  <filename>`, two spaces or `*<filename>` for binary mode).
 */
object Checksums {

    /** Expected lowercase SHA-256 hex digest for [fileName], or null if the file has no line. */
    fun expectedDigest(sumsText: String, fileName: String): String? {
        val wanted = fileName.trim()
        if (wanted.isEmpty()) return null
        sumsText.lineSequence().forEach { raw ->
            val line = raw.trimEnd('\r')
            val match = Regex("""^([0-9a-fA-F]{64})\s+\*?(.+)$""").find(line) ?: return@forEach
            val name = match.groupValues[2].trim()
            if (name == wanted) return match.groupValues[1].lowercase()
        }
        return null
    }

    fun sha256(file: File): String? = try {
        val digest = MessageDigest.getInstance("SHA-256")
        file.inputStream().use { input ->
            val buffer = ByteArray(64 * 1024)
            while (true) {
                val read = input.read(buffer)
                if (read == -1) break
                digest.update(buffer, 0, read)
            }
        }
        digest.digest().joinToString("") { "%02x".format(it) }
    } catch (_: Exception) { null }
}
