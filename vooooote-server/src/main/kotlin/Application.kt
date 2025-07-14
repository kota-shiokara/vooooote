package jp.ikanoshiokara

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        watchPaths = listOf("classes"),
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureKoinModules()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting()
    configureSwagger()
}
