package ir.pourahmadi.weather.data.repository


import ir.pourahmadi.weather.data.local.dao.CommonDao
import ir.pourahmadi.weather.data.remote.api.TopicApi
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.domain.repository.TopicRepository
import ir.pourahmadi.weather.utils.safeCall
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject


class TopicRepositoryImpl @Inject constructor(
    private val api: TopicApi,
    private val errorHandler: ErrorHandler,
    private val dao: CommonDao
) : TopicRepository {

    override suspend fun getTopics(fromHome: Boolean): Flow<BaseResult<List<TopicModel>>> {
        val tabs = if (fromHome) getHomeTabs() else getTabs()
        return safeCall(errorHandler,
            before = { BaseResult.Success(tabs) }) {
            val response = api.getTopics()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { tabs ->
                    dao.insertTopics(tabs.toTopicEntityList())
                    dao.insertHomeTopic(tabs.toHomeTopicsEntityList())
                    val tabData = if (fromHome) getHomeTabs() else getTabs()
                    BaseResult.Success(tabData)
                } ?: run {
                    BaseResult.GeneralError()
                }
            } else {
                throw HttpException(response)
            }
        }

    }

    private suspend fun getTabs(): List<TopicModel> {
        val result = dao.getTopicsAndSub().filter {
            it.subTopicList.isNotEmpty() ||
                    it.topic.parentId == null
        }
        return result.map { it.toTopicModel() }
    }

    private suspend fun getHomeTabs(): List<TopicModel> {
        val result = dao.getHomeTopicsAndSub().filter {
            it.homeSubTopicList.isNotEmpty() ||
                    it.homeTopic.parentId == null
        }
        return result.map { it.toTopicModel() }
    }
}