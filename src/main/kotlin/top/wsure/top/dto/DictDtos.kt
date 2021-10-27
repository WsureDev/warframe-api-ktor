package top.wsure.top.dto

import kotlinx.serialization.Serializable

@Serializable
data class Dict(
    val id:Long,

    val zh:String,

    val en:String,

){
    fun getByZh():Pair<String,
String>{
        return zh to en
    }
    fun getByEn():Pair<String,
String>{
        return en to zh
    }
}
@Serializable
data class LibItem(
    val id:Long,
    val name:String,
    val type:LibType,
    val uniqueName:String,
    val thumb:String,
)

enum class LibType {
    Arcanes,
    Archwing,
    `Arch-Gun`,
    `Arch-Melee`,
    Enemy,
    Fish,
    Gear,
    Melee,
    Mods,
    Pets,
    Primary,
    Resources,
    Secondary,
    Sentinels,
    Warframes,
    Relics
}