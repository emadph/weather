package ir.pourahmadi.weather.domain.use_case

import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.weather.WeatherModel
import ir.pourahmadi.weather.domain.repository.WeatherRepository
import ir.pourahmadi.weather.utils.Validate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val validation: Validate

) {
    suspend fun checkCityName(
        cityName: String
    ): Flow<BaseResult<Unit>> {
        val (resultBoolean, resultResId) = validation.validateCityName(
            cityName = cityName
        )
        if (!resultBoolean)
            return flow { emit(BaseResult.GeneralError(resultResId!!)) }

        return repository.checkCityName(cityName)
    }
    suspend fun getWeather(request: WeatherRequest): Flow<BaseResult<WeatherModel>> {
        return repository.getWeather(request)
    }
    suspend fun getWeatherOffline(): Flow<BaseResult<WeatherModel>> {
        return repository.getWeatherOffline()
    }
}