package ir.pourahmadi.weather.domain.use_case

import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.domain.repository.TopicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopicUseCase @Inject constructor(
    private val repository: TopicRepository
) {
    suspend fun getHomeTabs(fromHome: Boolean): Flow<BaseResult<List<TopicModel>>> {
        return repository.getTopics(fromHome)
    }
}