package jp.ikanoshiokara.vooooote.server

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jp.ikanoshiokara.vooooote.server.model.Proposal
import jp.ikanoshiokara.vooooote.server.model.Theme
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
        filter { call ->
            call.request.path().startsWith("/")
        }
    }
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            call.respondText("Hello, world!", status = HttpStatusCode.OK)
        }
        get("/ping") {
            call.respondText("pong", status = HttpStatusCode.OK)
        }
        get("/mock") {
            val proposals = mutableListOf(
                Proposal(0, "Mock Proposal")
            )
            val mockTheme = Theme(0, "Mock Theme", proposals)

            call.respond(mockTheme)
        }
    }
}