package top.wsure.top.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.top.dto.*
import top.wsure.top.utils.FileUtils.readFileJson
import top.wsure.top.utils.FileUtils.readResourceJson
import top.wsure.top.utils.HttpUtils.getJsonObject

object DictUtils {
    val logger: Logger = LoggerFactory.getLogger(javaClass)
    suspend fun downloadDict(): List<Dict> {
        return DictEnum.DICT.loadByEnum()?: emptyList()
    }

    suspend fun downloadInvasion(): List<Dict> {
        return DictEnum.INVASION.loadByEnum()?: emptyList()
    }

    suspend fun downloadNightWave(): List<Dict> {
        return DictEnum.NIGHT_WAVE.loadByEnum()?: emptyList()
    }

    suspend fun downloadLib(): List<LibItem> {
        return DictEnum.LIB.loadByEnum()?: emptyList()
    }

    suspend fun downloadSale(): List<Sale> {
        return DictEnum.SALE.loadByEnum()?: emptyList()
    }

    suspend fun downloadRiven(): List<Riven> {
        return DictEnum.RIVEN.loadByEnum()?: emptyList()
    }

    suspend inline fun <reified T> DictEnum.loadByEnum():T?{
        return this.remoteUrl().getJsonObject<T>().also {
            printLog(it,"remote request",this.fileName)
        }?: this.customUrl().readFileJson<T>().also {
            printLog(it,"customer file",this.fileName)
        } ?: this.localUrl().readResourceJson<T?>().also {
            printLog(it,"application resource",this.fileName)
        }
    }

    fun printLog(it:Any?,method:String,fileName:String){
        if(it == null) {
            logger.warn("Load {} by {} failure!",fileName,method)
        } else {
            logger.info("Load {} by {} success!",fileName,method)
        }
    }
}
