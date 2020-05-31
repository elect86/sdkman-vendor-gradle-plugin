package io.sdkman.vendors

import io.sdkman.vendors.model.SdkmanExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class SdkmanVendorPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create("sdkman", SdkmanExtension::class.java)

        target.tasks.create("sdkReleaseVersion", SdkReleaseVersionTask::class.java).configure()
        target.tasks.create("sdkDefaultVersion", SdkDefaultVersionTask::class.java).configure()
        target.tasks.create("sdkAnnounceVersion", SdkAnnounceVersionTask::class.java).configure()

        target.tasks.create("sdkMinorRelease", SdkMinorRelease::class.java).configure()
        target.tasks.create("sdkMajorRelease", SdkMajorRelease::class.java).configure()
    }

    private fun SdkmanVendorBaseTask.configureCommon(): Task =
        configureTask {
            apiUrl = apiUrl ?: project.extensions.getByName("sdkman.api") as String
            candidate = candidate ?: project.extensions.getByName("sdkman.candidate") as String
            version = version ?: project.extensions.getByName("sdkman.version") as String
            consumerKey = consumerKey ?: project.extensions.getByName("sdkman.consumerKey") as String
            consumerToken = consumerToken ?: project.extensions.getByName("sdkman.consumerToken") as String
        }

    private fun SdkReleaseVersionTask.configure(): Task {
        configureCommon()
        return configureTask {
            downloadUrl = downloadUrl ?: project.extensions.getByName("sdkman.url") as String
        }
    }

    private fun SdkDefaultVersionTask.configure(): Task = configureCommon()

    private fun SdkAnnounceVersionTask.configure(): Task {
        configureCommon()
        return configureTask {
            hashtag = hashtag ?: project.extensions.getByName("sdkman.hashtag") as String
        }
    }

    private fun SdkMinorRelease.configure(): Task {
        configureCommon()
        return configureTask {
            downloadUrl = downloadUrl ?: project.extensions.getByName("sdkman.url") as String
            hashtag = hashtag ?: project.extensions.getByName("sdkman.hashtag") as String
        }
    }

    companion object {
        private fun <T: SdkmanVendorBaseTask> T.configureTask(initializer: T.() -> Unit): Task {
            project.afterEvaluate { initializer() }
            return this
        }
    }
}