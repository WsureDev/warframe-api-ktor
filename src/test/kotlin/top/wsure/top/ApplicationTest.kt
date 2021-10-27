package top.wsure.top

import io.ktor.html.*
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import top.wsure.top.dto.Dict
import top.wsure.top.plugins.*
import top.wsure.top.utils.downloadDict
import kotlin.coroutines.suspendCoroutine

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
}