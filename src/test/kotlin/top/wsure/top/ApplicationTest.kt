package top.wsure.top

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.decoder.BrotliInputStream
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.css.Contain
import kotlinx.serialization.Serializable
import okio.ByteString.Companion.decodeHex
import okio.ByteString.Companion.toByteString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.top.dto.DictEnum
import top.wsure.top.plugins.configureMonitoring
import top.wsure.top.plugins.configureRouting
import top.wsure.top.utils.BiliLiveUtils
import top.wsure.top.utils.DictUtils.downloadDict
import top.wsure.top.utils.DictUtils.downloadRiven
import top.wsure.top.utils.JsonUtils.jsonToObject
import top.wsure.top.utils.JsonUtils.objectToJson
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.file.Paths
import java.util.zip.InflaterOutputStream
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    @Test
    fun testRoot() {

        withTestApplication({
            configureMonitoring()
            configureRouting()
        }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

    @Test
    @OptIn(DelicateCoroutinesApi::class)
    fun myTest() = runBlocking {
        val res = downloadDict()
        logger.info(res.toString())
        logger.info(res.objectToJson())
    }

    @Test
    fun testLocalDict() = runBlocking {
        val path = Paths.get("").toAbsolutePath().toString()
        logger.info("Working Directory = $path")
        val rivens = downloadRiven()
        logger.info(rivens.toString())
        logger.info(rivens.objectToJson())
    }

    @Test
    fun testEnum(){
        val t:TestEnumClass = ("{\n" +
                "    \"lib\": \"Dict\",\n" +
                "    \"name\": \"Dict\"\n" +
                "}").jsonToObject()
        logger.info(t.lib.remoteUrl())
    }

    @Serializable
    data class TestEnumClass(
        val lib:DictEnum,
        val name:String,
    )

    @Test
    fun testNumber(){
        val intents = 1.shl(0) + 1.shl(1)+ 1.shl(29)+ 1.shl(30)
        println(intents)
    }

    @Test
    fun testHex(){
        val hex = "000000f80010000100000007000000017b22756964223a3737373635362c22726f6f6d6964223a32313435323530352c2270726f746f766572223a332c22706c6174666f726d223a22776562222c2274797065223a322c226b6579223a225838746c7a34477a6e50514a42665577727537727545775a7a335f6648425a5f68323361554378564232734f4432743535793654645f796c59686c516234453172613977313377694f74504b5932736133625635444a6b5f526e4d63675651504c765f5770704f6e70446850565743666d61552d63326f6f486f446f4e6e6d53705039507a427669477152516771446f364f6f63684e773d227d"
        println(String(hex.substring(32).decodeHexX()))
        println(hex.length/2-4)
        println( BiliLiveUtils.readInt(hex.toUint8Array(),0,4))
    }
    fun String.decodeHexX(): ByteArray {
        check(length % 2 == 0) { "Must have an even length" }

        return chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()
    }
    fun String.toUint8Array(): Array<Int> {
        check(length % 2 == 0) { "Must have an even length" }

        return chunked(2)
            .map { it.toInt(16) }
            .toTypedArray()
    }
    @Test
    fun testZip(){
        val hex = "0000013f0010000300000005000000001b3002202c0a6c1bb6353c8a2eb4d95e363a3e8c59737b0453b9907e0bd564b51a0ef11f79d82c6369185a1ceae40e78a62b5dd21624760b3137b6f5db0c50b17ca3352d119d0e2aa2fa32784ea703db80ec510952455182d79b956d365641d233d0a5e7cdf5891ed5155912280257791eb8ab2176e5c1d6bf10d702aeb8c3719e4a502a9abe09ea02564733c30cc3d16175bb4a3f867889848cc350e193026522a21b45844a731c8dd8143f684732333d5d1526786eb9f6d784e661b733e1f72a8a4984009b0850b6be190b432385934e987b5f6312def0d447ced40cb345ed6151110b485cee9bac72980ed3499db7f2ee5570125c489c704c585647d50b87238a42be68e167f9c1990b0522b1c7ded87d0ccb9e6f580f6b92ac619f52e814fcccaddfc74fcf094c0c8b1408eb5b"
        val uint8Arr = hex.toUint8Array()
        val protocolVersion = BiliLiveUtils.readInt(uint8Arr,6,2)
        val packageLength = BiliLiveUtils.readInt(uint8Arr,0,4)
        val headLength = BiliLiveUtils.readInt(uint8Arr,4,2)
        println("protocolVersion:$protocolVersion ,packageLength:$packageLength headLength:$headLength")
        val bodyBuffer = hex.decodeHex().substring(headLength,packageLength).toByteArray() //
        val unzip = bodyBuffer.brotli()
        println(String(unzip))
        println(BiliLiveUtils.readInt(unzip.toByteString().hex().toUint8Array(),6,2))
    }
    fun ByteArray.zlib(): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val inflaterOutputStream = InflaterOutputStream(outputStream)
        inflaterOutputStream.write(this)
        inflaterOutputStream.close()
        return outputStream.toByteArray()
    }

    fun ByteArray.brotli():ByteArray{
        val inputStream = ByteArrayInputStream(this)
        val outputStream = ByteArrayOutputStream()
        Brotli4jLoader.ensureAvailability()
        val brotliInputStream = BrotliInputStream(inputStream)
        var read = brotliInputStream.read()
        while (read > -1) {
            outputStream.write(read)
            read = brotliInputStream.read()
        }
        return outputStream.toByteArray()
    }

    @Test
    fun testHeartbeat(){
        val hex = "000000140010000100000003000000000000000a5b6f626a656374204f626a6563745d"
        val arr = hex.decodeHex().substring(16).toByteArray().readUInt32BE()
        println(arr)
        val hbHex = "0000001f0010000100000002000000015b6f626a656374204f626a6563745d"
        val uint8Arr = hbHex.toUint8Array()
        val packageLength = BiliLiveUtils.readInt(uint8Arr,0,4)
        val headLength = BiliLiveUtils.readInt(uint8Arr,4,2)
        val protocolVersion = BiliLiveUtils.readInt(uint8Arr,6,2)
        val operation = BiliLiveUtils.readInt(uint8Arr,8,4)
        val sequenceId = BiliLiveUtils.readInt(uint8Arr,12,4)
        println("packageLength: $packageLength ,"+
                "headLength: $headLength ,"+
                "protocolVersion: $protocolVersion ,"+
                "operation: $operation ,"+
                "sequenceId: $sequenceId ")
        val body = hbHex.decodeHex().substring(16).toByteArray()
        println("body:${String(body)}")
        val byteArray = ByteArray(16)
        byteArray.write(31,4-1)
        byteArray.write(16,6-1)
        byteArray.write(1,8-1)
        byteArray.write(2,12-1)
        byteArray.write(1,16-1)
        println(byteArray.toByteString().hex() + "[object Object]".toByteArray().toByteString().hex())
    }

    fun ByteArray.readUInt32BE(offset: Int = 0): Long {
        throwOffsetError(this, offset, 4)
        return (((this[offset].toInt() and 0xFF).toLong() shl 24) or
                ((this[offset + 1].toInt() and 0xFF).toLong() shl 16) or
                ((this[offset + 2].toInt() and 0xFF).toLong() shl 8) or
                (this[offset + 3].toInt() and 0xFF).toLong())
//    return readUnsigned(this, 4, offset, false)
    }
    private fun throwOffsetError(byteArray: ByteArray, offset: Int, length: Int = 1, byteLength: Int = 0) {
        if (offset > byteArray.size - length - byteLength) throw IllegalArgumentException("The value of \"offset\" is out of range. It must be >= 0 and <= ${byteArray.size - length - byteLength}. Received ${offset}")
    }

    fun ByteArray.write(value:Int,offset: Int = 0){
        this[offset] = value.toByte()
    }
}