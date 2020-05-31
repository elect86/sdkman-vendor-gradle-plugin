package io.sdkman.vendors.validation

import org.gradle.api.GradleException
import org.gradle.api.Task
import javax.validation.ConstraintViolation

import javax.validation.Validation
import javax.validation.ValidatorFactory

class ConfigValidation {

    val validatorFactory = Validation.buildDefaultValidatorFactory()

    fun withValid(task: Task, func: () -> Unit) {
        val validator = validatorFactory.validator
        detectViolationsIn(task.name, validator.validate(task))
        func()
    }

    fun detectViolationsIn(name: String, constraints: Set<ConstraintViolation<Task>>) {
        if (constraints.isNotEmpty()) {
            val message = constraints.joinToString("; ") { "${it.propertyPath} ${it.message}" }
            throw GradleException("Invalid configuration for task $name: $message")
        }
    }
}
