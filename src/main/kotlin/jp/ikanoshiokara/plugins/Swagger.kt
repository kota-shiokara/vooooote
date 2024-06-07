package jp.ikanoshiokara.plugins

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.ktor.server.application.Application
import io.ktor.server.application.install

fun Application.configureSwagger() {
    install(SwaggerUI) {
        swagger {
            swaggerUrl = "swagger"
            forwardRoot = true
        }
        info {
            title = "Vooooote API"
            version = "latest"
            description = "Web API for voting service named Vooooote"

            contact {
                name = "kota-shiokara"
                url = "https://kota-shiokara.github.io/"
                email = "ikanoshiokara.fun@gmail.com"
            }

            license {
                name = "Apache 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.html"
            }
        }
        server {
            url = "http://localhost:8080"
            description = "Development Server"
        }
    }
}