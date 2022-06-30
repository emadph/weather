package ir.pourahmadi.weather.data.repository

import ir.pourahmadi.weather.data.local.dao.WeatherDao
import ir.pourahmadi.weather.data.local.entity.WeatherEntity
import ir.pourahmadi.weather.data.remote.api.WeatherApi
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherBaseResponse
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.model.weather.WeatherModel
import ir.pourahmadi.weather.domain.repository.WeatherRepository
import ir.pourahmadi.weather.utils.safeCall
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val errorHandler: ErrorHandler,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeather(request: WeatherRequest): Flow<BaseResult<WeatherModel>> {
        return safeCall(errorHandler) {
            val response = api.getWeather(request.toRequest())
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { bodyResponse ->
                    try {
                        dao.dropWeatherData()
                        dao.insertWeatherData(generateCache(bodyResponse.id, bodyResponse))
                    } catch (e: Exception) {
                    }
                    BaseResult.Success(generateOfflineModel())
                } ?: run {
                    BaseResult.GeneralError()
                }
            } else {
                throw HttpException(response)
            }
        }

    }

    override suspend fun getWeatherOffline(): Flow<BaseResult<WeatherModel>> {
        return safeCall(errorHandler) {
            BaseResult.Success(generateOfflineModel())
        }
    }

    override suspend fun checkCityName(cityName: String): Flow<BaseResult<Unit>> {
        return safeCall(errorHandler) {
            BaseResult.Success()
        }
    }

    private fun generateCache(
        id: Int,
        response: WeatherBaseResponse
    ): WeatherEntity {
        return WeatherEntity(
            id = id,
            cityName = response.cityName.toString(),
            cityLat = response.coordination?.lat.toString(),
            cityLng = response.coordination?.lng.toString(),
            temperature = response.mainWeatherInfo?.temp.toString(),
            maxTemperature = response.mainWeatherInfo?.temp_max.toString(),
            minTemperature = response.mainWeatherInfo?.temp_min.toString(),
            weatherIcon = response.weatherInfoCodes?.get(0)?.icon.toString(),
            weatherParametersType = response.weatherInfoCodes?.get(0)?.main.toString()
        )
    }


    private suspend fun generateOfflineModel(): WeatherModel? {
        val response = dao.getWeather()
        response?.let {
            return responseToOfflineModel(response)
        } ?: return null
    }

    private fun responseToOfflineModel(cacheEntity: WeatherEntity?): WeatherModel {
        return cacheEntity?.toWeatherOfflineModel()!!
    }

}