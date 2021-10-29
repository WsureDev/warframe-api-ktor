package top.wsure.top

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import top.wsure.top.dto.DictEnum
import top.wsure.top.plugins.*
import top.wsure.top.utils.downloadDict

class ApplicationTest {
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
        println(res)
        println(Json { prettyPrint = true }.encodeToString(res))
    }

    @Test
    fun testEnum(){
        val t:TestEnumClass = Json { prettyPrint = true }.decodeFromString("{\n" +
                "    \"lib\": \"Dict\",\n" +
                "    \"name\": \"Dict\"\n" +
                "}")
        println(t.lib.remoteUrl)
    }

    @Serializable
    data class TestEnumClass(
        val lib:DictEnum,
        val name:String,
    )

}