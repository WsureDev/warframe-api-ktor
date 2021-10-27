package top.wsure.top

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import top.wsure.top.plugins.*

fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation)
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureMonitoring()
        configureTemplating()
        configureSerialization()
        configureAdministration()
    }.start(wait = true)
}
