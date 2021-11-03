package top.wsure.top.utils

import io.ktor.client.request.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.top.Global
object HttpUtils {
    val logger: Logger = LoggerFactory.getLogger(javaClass)
    suspend inline fun <reified T> String.getJsonObject():T?{
        return kotlin.runCatching { Global.httpClient.get<T>(this) }
            .onFailure {
                logger.warn("Call url {} by get method failure !!",this,it)
            }.getOrNull()
    }
}
