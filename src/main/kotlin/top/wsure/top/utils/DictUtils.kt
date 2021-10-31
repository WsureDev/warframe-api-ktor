package top.wsure.top.utils

import top.wsure.top.dto.*

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

suspend fun downloadLib(): List<LibItem> {
    return DictEnum.LIB.remoteUrl.getJsonObject() ?: DictEnum.LIB.customUrl.readResourceJson()
    ?: DictEnum.LIB.localUrl.readResourceJson() ?: emptyList()
}

suspend fun downloadSale(): List<Sale> {
    return DictEnum.SALE.remoteUrl.getJsonObject() ?: DictEnum.SALE.customUrl.readResourceJson()
    ?: DictEnum.SALE.localUrl.readResourceJson() ?: emptyList()
}

suspend fun downloadRiven(): List<Riven> {
    return DictEnum.RIVEN.remoteUrl.getJsonObject() ?: DictEnum.RIVEN.customUrl.readResourceJson()
    ?: DictEnum.RIVEN.localUrl.readResourceJson() ?: emptyList()
}

