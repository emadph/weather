package ir.pourahmadi.weather.domain.repository

import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel
import kotlinx.coroutines.flow.Flow

interface WearherRepository {
    suspend fun getWearherList(url: String): Flow<BaseResult<List<WearherListModel>>>
    suspend fun getWearherListOffline(url: String): Flow<BaseResult<List<WearherListModel>>>
}