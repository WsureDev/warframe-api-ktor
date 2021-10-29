package top.wsure.top.utils

import io.ktor.client.request.*
import top.wsure.top.Global

suspend inline fun <reified T> String.getJsonObject():T?{
    return kotlin.runCatching { Global.httpClient.get<T>(this) }.getOrNull()
}