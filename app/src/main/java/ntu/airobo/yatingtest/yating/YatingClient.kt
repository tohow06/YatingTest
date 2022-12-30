package ntu.airobo.yatingtest.yating

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val TAG = "YatingClient"

class YatingClient {
    @Serializable
    data class PostBody(val pipeline: String, val option: String = "")

    suspend fun getAauthToken() {
        val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        val response: HttpResponse = client.post("https://asr.api.yating.tw/v1/token") {
            headers {
                append("key", "eb73e48099f07aa03fcf37652ce04d84284d84bb")
                append(HttpHeaders.ContentType, "application/json")
            }
            contentType(ContentType.Application.Json)
            setBody(PostBody("asr-zh-en-std"))
        }
        Log.d(TAG, "getAauthToken: " + response.bodyAsText())
        // TODO save auth_token
    }

    fun websocketsClient(){
        // TODO
    }
}