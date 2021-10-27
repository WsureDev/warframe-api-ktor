package top.wsure.top.vos

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val code: ResponseCode = ResponseCode.OK,
    val message: String? = null,
    val error: String? = null,
) {
}
enum class ResponseCode{
    OK,

}