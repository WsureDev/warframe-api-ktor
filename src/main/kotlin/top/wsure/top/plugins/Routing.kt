package top.wsure.top.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    install(AutoHeadResponse)
    install(DoubleReceive)

    routing {
        get("/") {
//            println(fileContent)
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        post("/double-receive") {
            val first = call.receiveText()
            val theSame = call.receiveText()
            call.respondText(first + " " + theSame)
        }
    }
}
