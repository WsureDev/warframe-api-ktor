package top.wsure.top.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import top.wsure.top.utils.getJsonObject

@Serializable
enum class DictEnum(val url:String) {
    @SerialName("Dict")
    DICT("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_Dict.json"),

    @SerialName("Invasion")
    INVASION("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_Invasion.json"),

    @SerialName("Lib")
    LIB("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_Lib.json"),

    @SerialName("NightWave")
    NIGHT_WAVE("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_NightWave.json"),

    @SerialName("Sale")
    SALE("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_Sale.json"),

    @SerialName("Riven")
    RIVEN("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_Riven.json"),

    ;

    suspend fun <T> downloadDict():List<T>{
        return this.url.getJsonObject()?: emptyList()
    }
}