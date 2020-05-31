package io.sdkman.vendors

import org.gradle.api.logging.Logger

interface ExceptionHandling {

    fun withTry(logger: Logger, func: () -> Unit) =
            try {
                func()
            } catch (e: Exception) {
                logger.error("Error: ${e.message}")
            }
}
