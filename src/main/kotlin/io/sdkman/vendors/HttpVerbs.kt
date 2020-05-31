package io.sdkman.vendors

import com.beust.klaxon.Parser
import khttp.responses.Response

interface HttpVerbs {

//    fun post(url: String, consumerKey:  String, consumerToken: String, values: Map): Response {
//        val r = khttp.post(url, headers(consumerKey, consumerToken), json = Parser.default().p)
//        r.type "application/json"
//        json values
//    }
//
//    Response put(RESTClient client, String path, String consumerKey, String consumerToken, Map values) {
//        client.put(path: path, headers: headers(consumerKey, consumerToken)) {
//        type "application/json"
//        json values
//    }
//    }

    fun headers(key: String, token: String): Map<String, String> =
            mapOf("consumer_key" to key, "consumer_token" to token)
}