package top.wsure.top.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.top.utils.JsonUtils.jsonToObject
import java.io.File

object FileUtils {
    val logger: Logger = LoggerFactory.getLogger(javaClass)
    inline fun <reified T> String.readResourceJson(): T? {
        return kotlin.runCatching { this::class.java.classLoader.getResource(this)?.readText()?.jsonToObject<T>() }
            .onFailure {
                logger.warn("Read resource file {} by classloader failure !!", this, it)
            }.getOrNull()
    }

    inline fun <reified T> String.readFileJson(): T? {
        return kotlin.runCatching { File(this).readText().jsonToObject<T>() }
            .onFailure {
                logger.warn("Read file {} by File method failure !!", this, it)
            }.getOrNull()
    }
}
