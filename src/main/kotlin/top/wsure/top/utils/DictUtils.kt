package top.wsure.top.utils

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.css.th
import top.wsure.top.Global
import top.wsure.top.dto.Dict
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.*

suspend fun downloadDict():List<Dict>{
    return HttpClient {
        install(JsonFeature){
            acceptContentTypes = acceptContentTypes + ContentType("text","plain")
        }
        engine {
            proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 7890))
        }
    }.get()
}

