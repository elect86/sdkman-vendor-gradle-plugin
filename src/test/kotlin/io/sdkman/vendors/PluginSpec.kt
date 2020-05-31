package io.sdkman.vendors

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.sdkman.vendors.model.sdkman
import org.gradle.testfixtures.ProjectBuilder

class PluginSpec : StringSpec() {

    val PLUGIN_ID = "io.sdkman.vendors"

    val project = ProjectBuilder.builder().build()

    init {

        project.apply(mapOf("plugin" to PLUGIN_ID))

        "should add tasks" {

            assert(project.tasks.getByName("sdkReleaseVersion") is SdkReleaseVersionTask)
            assert(project.tasks.getByPath("sdkAnnounceVersion") is SdkAnnounceVersionTask)
            assert(project.tasks.getByPath("sdkDefaultVersion") is SdkDefaultVersionTask)
            assert(project.tasks.getByPath("sdkMajorRelease") is SdkMajorRelease)
            assert(project.tasks.getByPath("sdkMinorRelease") is SdkMinorRelease)
        }

        "should add extension" {

            val sdkman = project.extensions.sdkman
            sdkman shouldNotBe null
            sdkman.api shouldBe "https://vendors.sdkman.io"
        }
    }
}