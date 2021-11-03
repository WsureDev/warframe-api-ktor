package top.wsure.top.service

import top.wsure.top.utils.DictUtils.downloadDict
import top.wsure.top.utils.DictUtils.downloadInvasion
import top.wsure.top.utils.DictUtils.downloadLib
import top.wsure.top.utils.DictUtils.downloadNightWave
import top.wsure.top.utils.DictUtils.downloadRiven
import top.wsure.top.utils.DictUtils.downloadSale

suspend fun initDictFollow(){
    val dictList = downloadDict()
    val invasionList = downloadInvasion()
    val nightWaveList = downloadNightWave()
    val libList = downloadLib()
    val saleList = downloadSale()
    val rivenList = downloadRiven()
    //todo transform to dictionary and catching in memory
}