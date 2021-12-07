package top.wsure.top

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import top.wsure.top.plugins.*
import top.wsure.top.utils.FileUtils
import top.wsure.top.utils.FileUtils.copyInputStreamToFile
import kotlin.io.path.Path


fun main() {
    initConfigFile()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation)
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureMonitoring()
        configureTemplating()
//        configureSerialization()
        configureAdministration()
    }.start(wait = true)
}

fun initConfigFile() {
    val path = "config/config.json"
    val config = Path(path).toAbsolutePath().toFile()
    val resource = FileUtils::class.java.getResourceAsStream(path)
    println(config.absolutePath)
    FileUtils.createFileAndDirectory(config,false)
    if(resource != null){
        println("copy")
        config.copyInputStreamToFile(resource)
    }

}
