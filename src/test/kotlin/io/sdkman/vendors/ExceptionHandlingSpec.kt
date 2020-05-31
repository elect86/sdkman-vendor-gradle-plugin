package io.sdkman.vendors

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.gradle.internal.logging.slf4j.OutputEventListenerBackedLogger

class ExceptionHandlingSpec : StringSpec() {

    val underTest = object : ExceptionHandling {}

//    val request = khttp.requests.Request
//    def response = Mock(HTTPResponse)

    val logger = OutputEventListenerBackedLogger("", null, null)

    init {

        "should successfully invoke function" {
            underTest.withTry(logger) { true } shouldBe true
        }

//        "should extract status and json message on rest client exception" {
//
//            underTest.withTry(logger) { throw Exception("boom!") }
//
//            then:
//            1 * response.getContentAsString() > > '{"message": "pow!"}'
//            1 * response.getStatusCode() > > 400
//            1 * log.error("Error: 400: pow!")
//        }

//    void "should extract status and raw message if invalid json on rest client exception"() {
//        when:
//        underTest.withTry(log, { throw new RESTClientException("boom!", request, response) })
//
//        then:
//        1 * response.getContentAsString() >> '<html>kapow</html>'
//        1 * response.getStatusCode() >> 400
//        1 * log.error("Error: 400: <html>kapow</html>")
//    }

    }
}