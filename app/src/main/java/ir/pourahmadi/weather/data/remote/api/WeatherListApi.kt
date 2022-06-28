package ir.pourahmadi.weather.data.remote.api

import ir.pourahmadi.weather.data.remote.dto.weather.WeatherBaseResponse
import retrofit2.Response
import retrofit2.http.*

interface WeatherListApi {

    @GET("weather")
    suspend fun getWeatherList(@QueryMap data: Map<String, String>): Response<WeatherBaseResponse>

}