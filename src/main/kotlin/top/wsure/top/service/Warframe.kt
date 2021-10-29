package top.wsure.top.service

import top.wsure.top.utils.downloadDict
import top.wsure.top.utils.downloadInvasion
import top.wsure.top.utils.downloadNightWave

suspend fun initDictFollow(){
    val dictList = downloadDict()
    val invasionList = downloadInvasion()
    val nightWaveList = downloadNightWave()
}