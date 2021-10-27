package top.wsure.top.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LibEnum(val url:String) {
    @SerialName("Dict")
    DICT("https://raw.githubusercontent.com/Richasy/WFA_Lexicon/WFA5/WF_Dict.json")
}