package ir.pourahmadi.weather.domain.repository

import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.weather.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(request: WeatherRequest): Flow<BaseResult<WeatherModel>>
    suspend fun getWeatherOffline(): Flow<BaseResult<WeatherModel>>
    suspend fun checkCityName(
        cityName: String
    ): Flow<BaseResult<Unit>>

}