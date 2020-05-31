package io.sdkman.vendors

import org.gradle.api.logging.Logger

interface ExceptionHandling {

    fun <R>withTry(logger: Logger, func: () -> R): R? =
            try {
                func()
            } catch (e: Exception) {
                logger.error("Error: ${e.message}")
                null
            }
}
