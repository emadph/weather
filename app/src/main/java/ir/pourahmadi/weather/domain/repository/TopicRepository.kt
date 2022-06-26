package ir.pourahmadi.weather.domain.repository

import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun getTopics(fromHome: Boolean): Flow<BaseResult<List<TopicModel>>>
}