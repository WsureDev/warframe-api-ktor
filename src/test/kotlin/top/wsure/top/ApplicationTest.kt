package top.wsure.top

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.top.dto.DictEnum
import top.wsure.top.plugins.configureMonitoring
import top.wsure.top.plugins.configureRouting
import top.wsure.top.utils.DictUtils.downloadDict
import top.wsure.top.utils.DictUtils.downloadRiven
import top.wsure.top.utils.JsonUtils.jsonToObject
import top.wsure.top.utils.JsonUtils.objectToJson
import java.nio.file.Paths
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

}