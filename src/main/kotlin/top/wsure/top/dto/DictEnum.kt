package top.wsure.top.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import top.wsure.top.Global
import top.wsure.top.utils.getJsonObject

@Serializable
enum class DictEnum(val remoteUrl:String,val localUrl:String,val customUrl:String) {
    @SerialName("Dict")
    DICT("${Global.WFA_LEXICON}WF_Dict.json","static/dicts/Dict.json","dicts/Dict.json"),

    @SerialName("Invasion")
    INVASION("${Global.WFA_LEXICON}WF_Invasion.json","static/dicts/Invasion.json","dicts/Invasion.json"),

    @SerialName("NightWave")
    NIGHT_WAVE("${Global.WFA_LEXICON}WF_NightWave.json","static/dicts/NightWave.json","dicts/NightWave.json"),

    @SerialName("Lib")
    LIB("${Global.WFA_LEXICON}WF_Lib.json","static/dicts/Lib.json","dicts/Lib.json"),

    @SerialName("Sale")
    SALE("${Global.WFA_LEXICON}WF_Sale.json","static/dicts/Sale.json","dicts/Sale.json"),

    @SerialName("Riven")
    RIVEN("${Global.WFA_LEXICON}WF_Riven.json","static/dicts/Riven.json","dicts/Riven.json"),

    ;
}