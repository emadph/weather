package ir.pourahmadi.weather.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.pourahmadi.weather.data.local.dao.WeatherDao
import ir.pourahmadi.weather.data.local.entity.WeatherListCacheEntity
import ir.pourahmadi.weather.data.remote.api.WeatherListApi
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherBaseResponse
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.model.news.WeatherModel
import ir.pourahmadi.weather.domain.repository.WeatherRepository
import ir.pourahmadi.weather.utils.safeCall
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherListApi,
    private val errorHandler: ErrorHandler,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherList(request: WeatherRequest): Flow<BaseResult<WeatherModel>> {
        return safeCall(errorHandler) {
            val response = api.getWeatherList(request.toRequest())
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { bodyResponse ->
                   dao.insertWeatherListCashe(generateCache(bodyResponse.id, bodyResponse))
                    BaseResult.Success(responseToModel(bodyResponse))
                } ?: run {
                    BaseResult.GeneralError()
                }
            } else {
                throw HttpException(response)
            }
        }

    }

    override suspend fun getWeatherListOffline(request: WeatherRequest): Flow<BaseResult<WeatherModel>> {
        return safeCall(errorHandler) {
            BaseResult.Success(generateModel(request))
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
    ): WeatherListCacheEntity {
        return WeatherListCacheEntity(
            id = id,
            cityName = response.cityName!!
        )
    }


    private suspend fun generateModel(request: WeatherRequest): WeatherModel? {
        val response = dao.getWeatherListCache(request.cityName.toString())
        response?.let {
            val type = object : TypeToken<WeatherBaseResponse>() {}.type
            val result = Gson().fromJson<WeatherBaseResponse>(response.toString(), type)
            return responseToModel(result)
        } ?: return null
    }

    private fun responseToModel(mList: WeatherBaseResponse): WeatherModel {
        return mList.toWeatherListModel() 
    }

}