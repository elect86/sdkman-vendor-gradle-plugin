package io.sdkman.vendors

open class SdkAnnounceVersionTask : SdkmanVendorBaseTask() {

    init {
        description = "Announce a Release on SDKMAN!"
    }

    var hashtag: String? = null

    override fun executeTask() {

        logger.quiet("Announcing for $candidate $version...")

        val values = mapOf("candidate" to candidate, "version" to version, "hashtag" to (hashtag ?: candidate))

        val response = khttp.post(apiUrl + ANNOUNCE_ENDPOINT, headers, json = values)

        logger.quiet("Response: ${response.statusCode}: ${response.text}...")
    }

    companion object {
        val ANNOUNCE_ENDPOINT = "/announce/struct"
    }
}

open class SdkDefaultVersionTask : SdkmanVendorBaseTask() {

    init {
        description = "Make an existing Candidate Version the new Default on SDKMAN!"
    }

    override fun executeTask() {

        logger.quiet("Releasing $candidate $version...")

        val values = mapOf("candidate" to candidate, "version" to version)

        val response = khttp.put(apiUrl + DEFAULT_ENDPOINT, headers, json = values)

        logger.quiet("Response: ${response.statusCode}: ${response.text}...")
    }

    companion object {
        val DEFAULT_ENDPOINT = "/default"
    }
}

open class SdkReleaseVersionTask : SdkmanVendorBaseTask() {

    var downloadUrl: String? = null

    init {
        description = "Release a new Candidate Version on SDKMAN!"
    }

    override fun executeTask() {

        logger.quiet("Releasing $candidate $version...")

        val values = mapOf("candidate" to candidate, "version" to version, "url" to downloadUrl)

        val response = khttp.post (apiUrl + RELEASE_ENDPOINT, headers, json = values)

        logger.quiet("Response: ${response.statusCode}: ${response.text}...")
    }

    companion object {
        val RELEASE_ENDPOINT = "/release"
    }
}
