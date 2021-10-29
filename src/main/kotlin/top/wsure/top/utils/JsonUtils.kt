package top.wsure.top.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonUtils {
    val formatter = Json{ ignoreUnknownKeys = true }

    inline fun <reified T:Any> T.toJson():String{
        return formatter.encodeToString(this)
    }

    inline fun <reified T> String.toObject():T {
        return formatter.decodeFromString(this)
    }
}