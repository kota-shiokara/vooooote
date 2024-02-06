package jp.ikanoshiokara

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import jp.ikanoshiokara.plugins.configureHTTP
import jp.ikanoshiokara.plugins.configureRouting
import jp.ikanoshiokara.plugins.configureSerialization
import jp.ikanoshiokara.plugins.configureSwagger


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureSwagger()
    configureRouting()
    configureSerialization()
}
