package top.wsure.top.utils

import top.wsure.top.dto.Dict
import top.wsure.top.dto.DictEnum

suspend fun downloadDict():List<Dict>{
    return DictEnum.DICT.url.getJsonObject()?: emptyList()
}

