package ir.pourahmadi.weather.data.repository.home

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.dto.category.TopicResponse
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.utils.convertJsonToModel
import ir.pourahmadi.weather.utils.enqueueResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import mockwebserver3.MockWebServer
import org.junit.*
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeRepositoryMockTest {
    private val TAG = HomeRepositoryMockTest::class.simpleName

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: Database

    @Inject
    @Named("MockWebServer")
    lateinit var mockWebServer: MockWebServer

    @Inject
    @Named("HomeRepositoryMock")
    lateinit var repo: TopicRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getHomeTabs_onTestApi() {
        mockWebServer.enqueueResponse("homeTopics-200.json", 200)

        runBlocking {
            repo.getTopics()
                .collect { baseResult ->
                    assertThat(baseResult is BaseResult.Success).isTrue()
                }
        }
    }

    @Test
    fun getHomeTabsWithSave_onTestApi() {
        mockWebServer.enqueueResponse(
            "homeTopics-200.json",
            200
        )
        val responseBody = mockWebServer.dispatcher.peek()
            .getBody()?.readString(StandardCharsets.UTF_8)
            ?.convertJsonToModel(TopicResponse::class.java)
        if (responseBody == null)
            Assert.fail("** Info Is Null **")

        runBlocking {
            repo.getTopics()
                .collect { baseResult ->
                    assertThat(baseResult is BaseResult.Success).isTrue()
                    val topics = database.commonDao.getTopics()
                    assertThat(topics)
                        .isNotEmpty()
                    assertThat(topics.size)
                        .isEqualTo(5)
                }
        }
    }


}