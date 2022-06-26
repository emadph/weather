package ir.pourahmadi.weather.utils


import com.google.gson.Gson
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets


fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    if (fileName.isBlank()) {
        enqueue(
            MockResponse()
                .setResponseCode(code)
        )
    } else {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }
}

fun <T> String.convertJsonToModel(modelClass: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(this, modelClass)
}

fun MockWebServer.registerApiRequest(httpRequest: HttpRequest, responseBody: String) {
    val mockServerDispatcher = dispatcher as MockServerDispatcher

    mockServerDispatcher.registerApiRequest(httpRequest, responseBody)
}
