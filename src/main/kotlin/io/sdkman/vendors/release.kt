package io.sdkman.vendors

open class SdkMinorRelease : SdkmanVendorBaseTask() {

    var downloadUrl: String? = null
    var hashtag: String? = null

    init {
        description = "Convenience task performs a Minor Release consisting of Release and Announce combined on SDKMAN!"
    }

    override fun executeTask() {
        logger.quiet("Releasing $candidate $version...")
        val releaseValues = mapOf("candidate" to "candidate", "version" to version, "url" to downloadUrl)
        val releaseResponse = khttp.post(apiUrl + RELEASE_ENDPOINT, headers, json = releaseValues)
        logger.quiet("Response: ${releaseResponse.statusCode}: ${releaseResponse.text}...")

        logger.quiet("Announcing for $candidate $version...")
        val announceValues = mapOf("candidate" to candidate, "version" to version, "hashtag" to (hashtag ?: candidate))
        val announceResponse = khttp.post(apiUrl + ANNOUNCE_ENDPOINT, headers, json = announceValues)
        logger.quiet("Response: ${announceResponse.statusCode}: ${announceResponse.text}...")
    }

    companion object {
        val ANNOUNCE_ENDPOINT = "/announce/struct"
        val RELEASE_ENDPOINT = "/release"
    }
}

open class SdkMajorRelease : SdkMinorRelease() {

    init {
        description = "Convenience task performs a Major Release consisting of Release, Default and Announce combined on SDKMAN!"
    }

    override fun executeTask() {
        super.executeTask()
        logger.quiet("Defaulting $candidate $version...")
        val values = mapOf("candidate" to candidate, "version" to version)
        val response = khttp.put (apiUrl + DEFAULT_ENDPOINT, headers, json = values)
        logger.quiet("Response: ${response.statusCode}: ${response.text}...")
    }

    companion object {
        val DEFAULT_ENDPOINT = "/default"
    }
}

