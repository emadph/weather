package ir.pourahmadi.weather.domain.repository

import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.news.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherList(request: WeatherRequest): Flow<BaseResult<WeatherModel>>
    suspend fun getWeatherListOffline(request: WeatherRequest): Flow<BaseResult<WeatherModel>>
    suspend fun checkCityName(
        cityName: String
    ): Flow<BaseResult<Unit>>

}