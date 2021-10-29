package top.wsure.top.utils

import top.wsure.top.dto.Dict
import top.wsure.top.dto.DictEnum

suspend fun downloadDict(): List<Dict> {
    return DictEnum.DICT.remoteUrl.getJsonObject() ?: DictEnum.DICT.customUrl.readResourceJson()
    ?: DictEnum.DICT.localUrl.readResourceJson() ?: emptyList()
}

suspend fun downloadInvasion(): List<Dict> {
    return DictEnum.INVASION.remoteUrl.getJsonObject() ?: DictEnum.INVASION.customUrl.readResourceJson()
    ?: DictEnum.INVASION.localUrl.readResourceJson() ?: emptyList()
}

suspend fun downloadNightWave(): List<Dict> {
    return DictEnum.NIGHT_WAVE.remoteUrl.getJsonObject() ?: DictEnum.NIGHT_WAVE.customUrl.readResourceJson()
    ?: DictEnum.NIGHT_WAVE.localUrl.readResourceJson() ?: emptyList()
}

