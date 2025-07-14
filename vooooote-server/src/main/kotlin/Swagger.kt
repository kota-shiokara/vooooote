package jp.ikanoshiokara

import io.github.smiley4.ktoropenapi.OpenApi
import io.github.smiley4.ktoropenapi.openApi
import io.github.smiley4.ktorswaggerui.swaggerUI
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureSwagger() {
    install(OpenApi)
    routing {
        route("api.json") {
            openApi()
        }

        route("swagger") {
            swaggerUI("/api.json")
        }
    }
}