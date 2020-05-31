package io.sdkman.vendors

import io.sdkman.vendors.validation.ConfigValidation
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.hibernate.validator.constraints.URL

abstract class SdkmanVendorBaseTask : DefaultTask(), ExceptionHandling, HttpVerbs {

    val configValidation = ConfigValidation()

    @Input
    @URL
    var apiUrl: String? = null

    @Input
    var candidate: String? = null

    @Input
    var version: String? = null
    var consumerKey: String? = null
    var consumerToken: String? = null

    @TaskAction
    fun start() {
        withTry(logger) {
            configValidation.withValid(this) {
                executeTask()
            }
        }
    }

    abstract fun executeTask()

    val headers: Map<String, String>
        get() = headers(consumerKey!!, consumerToken!!)
}
