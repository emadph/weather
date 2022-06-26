package ir.pourahmadi.weather.data.repository.profile

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.api.ProfileApi
import ir.pourahmadi.weather.data.remote.dto.profile.InfoRequest
import ir.pourahmadi.weather.data.remote.dto.profile.InfoResponse
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.utils.convertJsonToModel
import ir.pourahmadi.weather.utils.enqueueResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import mockwebserver3.MockWebServer
import org.junit.*
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
class ProfileRepositoryMockTest {
    private val TAG = ProfileRepositoryMockTest::class.simpleName

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: Database

    @Inject
    @Named("MockWebServer")
    lateinit var mockWebServer: MockWebServer

    @Inject
    @Named("ProfileApiMock")
    lateinit var api: ProfileApi

    @Inject
    @Named("ProfileRepositoryMock")
    lateinit var aut: ProfileRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun checkPublicName_onTestApi() {
        mockWebServer.enqueueResponse("", 200)

        runBlocking {
            aut.checkPublicName("hadidehghani")
                .collect { baseResult ->
                    assertThat(baseResult is BaseResult.Success).isTrue()
                }
        }
    }

    @Test
    fun saveProfile_onTestApi() {
        mockWebServer.enqueueResponse("profile-info-200.json", 200)
        val profile = InfoRequest(publicName = "hadijoon")
        val responseBody = mockWebServer.dispatcher.peek()
            .getBody()?.readString(StandardCharsets.UTF_8)
            ?.convertJsonToModel(InfoResponse::class.java)
        if (responseBody == null)
            Assert.fail("** Info Is Null **")
        val userExpect = responseBody?.toUserEntity()

        runBlocking {
            aut.saveProfile(profile)
                .collect { baseResult ->
                    assertThat(baseResult is BaseResult.Success).isTrue()

                    val user = database.userDao.getUser()
                    if (user.isNotEmpty()) {
                        assertThat(user[0])
                            .isEqualTo(
                                userExpect
                            )
                    } else {
                        Assert.fail("**User not Insert to Database**")
                    }
                }
        }
    }


}