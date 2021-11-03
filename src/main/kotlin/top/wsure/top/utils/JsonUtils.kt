package top.wsure.top.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object JsonUtils {
    val formatter = Json{ ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T:Any> T.objectToJson():String{
        return formatter.encodeToString(this)
    }

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> String.jsonToObject():T {
        return formatter.decodeFromString(this)
    }
}