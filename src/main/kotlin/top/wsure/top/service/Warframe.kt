package top.wsure.top.service

import top.wsure.top.utils.*

suspend fun initDictFollow(){
    val dictList = downloadDict()
    val invasionList = downloadInvasion()
    val nightWaveList = downloadNightWave()
    val libList = downloadLib()
    val saleList = downloadSale()
    val rivenList = downloadRiven()
}