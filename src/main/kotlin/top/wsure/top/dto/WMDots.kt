package top.wsure.top.dto

import kotlinx.serialization.Serializable

@Serializable
data class Sale(
    val marketId: String,
    val id: Long,
    val code: String,
    val main: String,
    val component: String?,
    val zh: String,
    val en: String,
    val thumb: String,
)