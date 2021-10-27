package top.wsure.top.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    install(AutoHeadResponse)
    install(DoubleReceive)
    val fileContent = this::class.java.classLoader.getResource("static/dicts/base/Dict.json")?.readText()

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
