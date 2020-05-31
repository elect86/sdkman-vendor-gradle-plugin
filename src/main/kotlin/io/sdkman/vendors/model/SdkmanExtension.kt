package io.sdkman.vendors.model

import org.gradle.api.plugins.ExtensionContainer
import org.hibernate.validator.constraints.URL

open class SdkmanExtension {

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

val ExtensionContainer.sdkman: SdkmanExtension
    get() = getByName("sdkman") as SdkmanExtension