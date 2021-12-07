package top.wsure.top.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.top.utils.JsonUtils.jsonToObject
import java.io.*
import java.util.*

object FileUtils {
    val logger: Logger = LoggerFactory.getLogger(javaClass)
    inline fun <reified T> String.readResourceJson(): T? {
        return kotlin.runCatching { FileUtils::class.java.classLoader.getResource(this)?.readText()?.jsonToObject<T>() }
            .onFailure {
                logger.warn("Read resource file {} by classloader failure !!", this, it)
            }.getOrNull()
    }

    inline fun <reified T> String.readFileJson(): T? {
        return kotlin.runCatching { File(this).readText().jsonToObject<T>() }
            .onFailure {
                logger.warn("Read file {} by File method failure !!", this, it)
            }.getOrNull()
    }

    fun createFileAndDirectory(file:File,isDir:Boolean){
        if(isDir) file.mkdirs()
        else if(file.parentFile.exists() || file.parentFile.mkdirs()){
            file.createNewFile()
        }
    }

    fun File.copyInputStreamToFile(inputStream: InputStream) {
        this.outputStream().use { fileOut ->
            inputStream.copyTo(fileOut)
        }
    }
}
