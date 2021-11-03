package top.wsure.top.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import top.wsure.top.Global

@Serializable
enum class DictEnum(val fileName:String) {
    @SerialName("Dict")
    DICT("Dict.json"),

    @SerialName("Invasion")
    INVASION("Invasion.json"),

    @SerialName("NightWave")
    NIGHT_WAVE("NightWave.json"),

    @SerialName("Lib")
    LIB("Lib.json"),

    @SerialName("Sale")
    SALE("Sale.json"),

    @SerialName("Riven")
    RIVEN("Riven.json"),

    ;
    fun remoteUrl():String{
        return "${Global.WFA_LEXICON}WF_${this.fileName}"
    }
    fun localUrl():String{
        return "${Global.RESOURCE_LEXICON}WF_${this.fileName}"
    }
    fun customUrl():String{
        return "${Global.CUSTOMER_LEXICON}WF_${this.fileName}"
    }
}