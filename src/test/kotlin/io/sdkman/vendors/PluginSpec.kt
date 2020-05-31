package io.sdkman.vendors

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.gradle.testfixtures.ProjectBuilder

class PluginSpec : StringSpec() {

    val PLUGIN_ID = "io.sdkman.vendors"

    val project = ProjectBuilder.builder().build()

    init {

        project.apply(mapOf("plugin" to PLUGIN_ID))

        "should add tasks" {

            shouldBeInstanceOf<SdkReleaseVersionTask> { project.tasks.getByName("sdkReleaseVersion") }
            shouldBeInstanceOf<SdkAnnounceVersionTask> { project.tasks.getByPath("sdkAnnounceVersion") }
            shouldBeInstanceOf<SdkDefaultVersionTask> { project.tasks.getByPath("sdkDefaultVersion") }
            shouldBeInstanceOf<SdkMajorRelease> { project.tasks.getByPath("sdkMajorRelease") }
            shouldBeInstanceOf<SdkMinorRelease> { project.tasks.getByPath("sdkMinorRelease") }
        }

        "should add extension" {

            project.extensions.getByName("sdkman") shouldNotBe null
            project.extensions.getByName("sdkman.api") shouldBe "https://vendors.sdkman.io"
        }
    }
}