package top.wsure.top.dto

data class Riven(
    val id: Long,
    val name: String,
    val thumb: String,
    val type: RivenType,
    val rank: Int,
    val modulus: Double,
)

enum class RivenType {
    Archgun,
    Melee,
    Rifle,
    Shotgun,
    Pistol,
    Zaw,
    Kitgun,
    Secondary,
    Primary
}