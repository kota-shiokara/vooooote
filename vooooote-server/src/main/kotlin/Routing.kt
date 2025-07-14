package jp.ikanoshiokara

import io.github.smiley4.ktoropenapi.delete
import io.github.smiley4.ktoropenapi.get
import io.github.smiley4.ktoropenapi.post
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import jp.ikanoshiokara.omikuji.OmikujiPayload
import jp.ikanoshiokara.omikuji.OmikujiRepository
import jp.ikanoshiokara.omikuji.SomeOmikujiResponse
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import kotlin.reflect.typeOf

fun Application.configureRouting() {
    routing {
        get(
            path = "/health",
            builder = {
                response {
                    HttpStatusCode.OK to {
                        description = "A success response"
                        body<Map<String, String>> {
                            example(
                                name = "Health check word: Hello" ,
                                example = {
                                    value = mapOf("message" to "Hello, vooooote!")
                                }
                            )
                        }
                    }
                }
            }
        ) {
            call.respond(mapOf("message" to "Hello, vooooote!"))
        }

        get(
            path = "/omikuji",
            builder = {
                request {
                    queryParameter(name = "n", type = typeOf<Int>()) {
                        description = "Maximum number of responses"
                        required = false
                    }
                }
                response {
                    HttpStatusCode.OK to {
                        description = "Get Omikuji Success"
                        body<SomeOmikujiResponse> {
                            example(
                                name = "Omikuji" ,
                                example = {
                                    value = SomeOmikujiResponse.SAMPLE
                                }
                            )
                        }
                    }
                }
            }
        ) {
            val repository: OmikujiRepository by inject()
            val received = call.parameters["n"]
            val count = received?.toInt() ?: 0
            when (count) {
                in 1..Int.MAX_VALUE -> {
                    val someOmikuji = (1..count).map {
                        repository.readRandom(1).someOmikuji.first()
                    }
                    val someOmikujiResponse = SomeOmikujiResponse(
                        totalCount = someOmikuji.size,
                        someOmikuji = someOmikuji
                    )
                    call.respond(someOmikujiResponse)
                }
                0 -> {
                    val someOmikuji = repository.readAll()
                    call.respond(someOmikuji)
                }
                else -> {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }

        post(
            path = "/omikuji",
            builder = {
                request {
                    body<OmikujiPayload> {
                        example(
                            name = "Omikuji Example",
                            example = {
                                value = OmikujiPayload.SAMPLE
                            }
                        )
                    }
                }
                response {
                    HttpStatusCode.OK to {
                        description = "Create Omikuji Success"
                        body<SomeOmikujiResponse> {
                            example(
                                name = "Omikuji" ,
                                example = {
                                    value = SomeOmikujiResponse.SAMPLE
                                }
                            )
                        }
                    }
                }
            }
        ) {
            val repository: OmikujiRepository by inject()
            val payload: OmikujiPayload = call.receive()
            val response = repository.create(payload)
            call.respond(response)
        }

        get(
            path = "/omikuji/{id}",
            builder = {
                request {
                    pathParameter(name = "id", type = typeOf<Int>()) {
                        description = "Get by Omikuji Id"
                        required = true
                    }
                }
                response {
                    HttpStatusCode.OK to {
                        description = "Get Omikuji Success"
                        body<SomeOmikujiResponse> {
                            example(
                                name = "Omikuji" ,
                                example = {
                                    value = SomeOmikujiResponse.SAMPLE
                                }
                            )
                        }
                    }
                }
            }
        ) {
            val repository: OmikujiRepository by inject()
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val someOmikuji = repository.read(id = id)
            if (someOmikuji != null) {
                call.respond(someOmikuji)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete(
            path = "/omikuji/{id}",
            builder = {
                request {
                    pathParameter(name = "id", type = typeOf<Int>()) {
                        description = "Delete by Omikuji Id"
                        required = true
                    }
                }
                response {
                    HttpStatusCode.OK to {
                        description = "Delete Omikuji Success"
                    }
                }
            }
        ) {
            val repository: OmikujiRepository by inject()
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            repository.delete(id = id)
            call.respond(HttpStatusCode.OK)
        }
    }
}
