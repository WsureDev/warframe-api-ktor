package top.wsure.top.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dict(
    val id:Long,

    val zh:String,

    var en:String,

)

@Serializable
data class LibItem(
    val id:Long,
    val name:String,
    val type:LibType,
    val uniqueName:String,
    val thumb:String,
)

@Serializable
enum class LibType {
    @SerialName("Arcanes")
    ARCANES,
    @SerialName("Archwing")
    ARCHWING,
    @SerialName("Arch-Gun")
    ARCH_GUN,
    @SerialName("Arch-Melee")
    ARCH_MELEE,
    @SerialName("Enemy")
    ENEMY,
    @SerialName("Fish")
    FISH,
    @SerialName("Gear")
    GEAR,
    @SerialName("Melee")
    MELEE,
    @SerialName("Mods")
    MODS,
    @SerialName("Pets")
    PETS,
    @SerialName("Primary")
    PRIMARY,
    @SerialName("Resources")
    RESOURCES,
    @SerialName("Secondary")
    SECONDARY,
    @SerialName("Sentinels")
    SENTINELS,
    @SerialName("Warframes")
    WARFRAMES,
    @SerialName("Relics")
    RELICS
}