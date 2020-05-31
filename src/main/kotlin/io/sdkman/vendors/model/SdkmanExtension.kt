package io.sdkman.vendors.model

import org.hibernate.validator.constraints.URL

class SdkmanExtension {

    @URL
    val api = "https://vendors.sdkman.io"

    var consumerKey: String? = null

    var consumerToken: String? = null

    var candidate: String? = null

    var version: String? = null

    @URL
    var url: String? = null

    var hashtag: String? = null

    override fun toString(): String =
            "SdkmanExtension{api='$api', consumerKey='$consumerKey', consumerToken='$consumerToken', " +
                    "candidate='$candidate', version='$version', url='$url', hashtag='$hashtag'}"
}