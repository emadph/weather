package ir.pourahmadi.weather.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.pourahmadi.weather.data.local.dao.WearherDao
import ir.pourahmadi.weather.data.local.entity.WearherListCacheEntity
import ir.pourahmadi.weather.data.remote.api.WearherListApi
import ir.pourahmadi.weather.data.remote.dto.Wearher.WearherLIstResponse
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel
import ir.pourahmadi.weather.domain.repository.WearherRepository
import ir.pourahmadi.weather.utils.safeCall
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject

class WearherRepositoryImpl @Inject constructor(
    private val api: WearherListApi,
    private val errorHandler: ErrorHandler,
    private val WearherDao: WearherDao
) : WearherRepository {

    override suspend fun getWearherList(url: String): Flow<BaseResult<List<WearherListModel>>> {
        return safeCall(errorHandler) {
            val response = api.getWearherList(url)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { WearherList ->
                    WearherDao.insertWearherListCashe(generateCache(url, WearherList))
                    BaseResult.Success(responseToModel(WearherList))
                } ?: run {
                    BaseResult.GeneralError()
                }
            } else {
                throw HttpException(response)
            }
        }

    }

    override suspend fun getWearherListOffline(url: String): Flow<BaseResult<List<WearherListModel>>> {
        return safeCall(errorHandler) {
            BaseResult.Success(generateModel(url))
        }
    }

    private fun generateCache(
        url: String,
        response: List<WearherLIstResponse>
    ): WearherListCacheEntity {
        val serialized = Gson().toJson(response)
        return WearherListCacheEntity(
            url = url,
            response = serialized
        )
    }


    private suspend fun generateModel(url: String): List<WearherListModel> {
        val response = WearherDao.getWearherListCache(url)
        response?.let {
            val type = object : TypeToken<List<WearherLIstResponse>>() {}.type
            val result = Gson().fromJson<List<WearherLIstResponse>>(response.response, type)
            return responseToModel(result)
        } ?: return listOf()
    }

    private fun responseToModel(mList: List<WearherLIstResponse>): List<WearherListModel> {
        return mList.map { it.toWearherListModel() }
    }

}