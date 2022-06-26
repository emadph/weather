package ir.pourahmadi.weather.data.remote.api

import ir.pourahmadi.weather.data.remote.dto.category.TopicResponse
import retrofit2.Response
import retrofit2.http.GET

interface TopicApi {

    @GET("common/api/v1/home")
    suspend fun getTopics(): Response<TopicResponse>

}