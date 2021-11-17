package top.wsure.top.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Riven(
    val id: Long,
    val name: String,
    val thumb: String,
    val type: RivenType,
    val rank: Int,
    val modulus: Double,
)

@Serializable
enum class RivenType {
    @SerialName("Archgun")
    ARCHGUN,
    @SerialName("Melee")
    MELEE,
    @SerialName("Rifle")
    RIFLE,
    @SerialName("Shotgun")
    SHOTGUN,
    @SerialName("Pistol")
    PISTOL,
    @SerialName("Zaw")
    ZAW,
    @SerialName("Kitgun")
    KITGUN,
    @SerialName("Secondary")
    SECONDARY,
    @SerialName("Primary")
    PRIMARY
}