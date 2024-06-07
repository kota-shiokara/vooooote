package jp.ikanoshiokara.plugins

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable


fun Application.configureRouting() {
    routing {
        get("/", {
            description = "Root"
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<RootResponse> { description = "the response" }
                }
            }
        }) {
            call.respond(RootResponse("Hello, World!", "0.0.3"))
        }
    }
}

@Serializable
data class RootResponse(val content: String, val version: String)