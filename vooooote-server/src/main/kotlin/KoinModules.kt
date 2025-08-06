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
                val driver = environment.config.property("db.driver").getString()
                val url = environment.config.property("db.url").getString()
                val user = environment.config.property("db.user").getString()
                val password = environment.config.property("db.password").getString()

                Database.connect(
                    url = url,
                    user = user,
                    driver = driver,
                    password = password,
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
