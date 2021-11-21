package top.wsure.top.utils

import okio.ByteString
import kotlin.math.pow

object BiliLiveUtils {
    fun readInt(rawMessage: ByteString,start:Int,length:Int):Int{
        var result = 0
        val now = rawMessage.toByteArray().drop(start).take(length)
        for (i in now){
            result += (256.toDouble().pow(i.toInt()).toInt() * now[length-i-1])
        }
        return result
    }

    fun readInt(rawMessage: Array<Int>,start:Int,length:Int):Int{
        var result = 0
        val now = rawMessage.drop(start).take(length)
        for ((i , _) in now.withIndex()){
            result += (256.toDouble().pow(i).toInt() * now[length-i-1])
        }
        return result
    }
}