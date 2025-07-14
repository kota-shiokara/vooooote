package jp.ikanoshiokara

import io.ktor.server.application.Application
import io.ktor.server.application.install
import jp.ikanoshiokara.omikuji.OmikujiRepository
import jp.ikanoshiokara.omikuji.OmikujiRepositoryImpl
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoinModules() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<HelloService> {
                HelloService {
                    println(environment.log.info("Hello, World!"))
                }
            }
            single<Database>(
                createdAtStart = true
            ) {
                Database.connect(
                    url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                    user = "root",
                    driver = "org.h2.Driver",
                    password = "",
                )
            }
            single<OmikujiRepository> {
                OmikujiRepositoryImpl(
                    database = get<Database>()
                )
            }
        })
    }
}
