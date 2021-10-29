package top.wsure.top.utils

import top.wsure.top.utils.JsonUtils.toObject


inline fun <reified T> String.readResourceJson():T?{
    return kotlin.runCatching { this::class.java.classLoader.getResource(this)?.readText()?.toObject<T>() }.getOrNull()
}