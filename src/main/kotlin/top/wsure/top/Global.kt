package top.wsure.top

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.http.*

object Global {
    const val WFA_LEXICON = "https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/"
    val httpClient:HttpClient = HttpClient(CIO){
        install(JsonFeature){
            acceptContentTypes = acceptContentTypes + ContentType("text","plain")
        }
    }
}