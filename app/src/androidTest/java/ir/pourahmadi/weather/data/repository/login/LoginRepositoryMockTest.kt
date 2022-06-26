package ir.pourahmadi.weather.data.repository.login

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.api.LoginApi
import ir.pourahmadi.weather.data.remote.dto.login.LoginResponse
import ir.pourahmadi.weather.data.remote.dto.login.OtpRequest
import ir.pourahmadi.weather.data.remote.dto.login.OtpResponse
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorsString
import ir.pourahmadi.weather.utils.SharedPrefs
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
class LoginRepositoryMockTest {
    private val TAG = LoginRepositoryMockTest::class.simpleName

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: Database

    @Inject
    @Named("MockWebServer")
    lateinit var mockWebServer: MockWebServer

    @Inject
    @Named("SharedPrefs")
    lateinit var shPref: SharedPrefs

    @Inject
    @Named("LoginApiMock")
    lateinit var api: LoginApi

    @Inject
    @Named("LoginRepositoryMock")
    lateinit var aut: LoginRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun sendOtp_onTestApi() {
        mockWebServer.enqueueResponse("send-otp-200.json", 200)
        val responseBody = mockWebServer.dispatcher.peek()
            .getBody()?.readString(StandardCharsets.UTF_8)
            ?.convertJsonToModel(OtpResponse::class.java)
        val expect = responseBody?.toOtpModel()
        val otpRequest = OtpRequest(
            phoneNumber = "09353588937",
            countryCode = "+98"
        )
        runBlocking {
            aut.sendOtp(otpRequest)
                .collect { baseResult ->
                    if (baseResult is BaseResult.Success && expect != null) {
                        assertThat(baseResult.value).isEqualTo(expect)
                    } else {
                        Assert.fail("** Result Not Success **")
                    }
                }
        }
    }

    @Test
    fun login_with_publicName_onTestApi() {
        mockWebServer.enqueueResponse("login-200.json", 200)
        val responseBody = mockWebServer.dispatcher.peek()
            .getBody()?.readString(StandardCharsets.UTF_8)
            ?.convertJsonToModel(LoginResponse::class.java)
        val expect = responseBody?.info?.toUserEntity()?.toUserModel()

        runBlocking {
            aut.login(
                notificationId = "",
                countryCode = "+98",
                phoneNo = "09353588937",
                otpCode = "6804"
            )
                .collect { baseResult ->
                    if (baseResult is BaseResult.Success && expect != null) {
                        assertThat(baseResult.value).isEqualTo(expect)
                    } else {
                        Assert.fail("** Result Not Success **")
                    }
                }
        }
    }

    @Test
    fun login_with_publicName_null_onTestApi() {
        mockWebServer.enqueueResponse("login-publicname-null-200.json", 200)
        val expect = ErrorsString.USER_NULL

        runBlocking {
            aut.login(
                notificationId = "",
                countryCode = "+98",
                phoneNo = "09353588937",
                otpCode = "6804"
            )
                .collect { baseResult ->
                    if (baseResult is BaseResult.GeneralError) {
                        assertThat(baseResult.redId).isEqualTo(expect)
                    } else {
                        Assert.fail("** Result Not Success **")
                    }
                }
        }
    }

    @Test
    fun login_with_info_null_onTestApi() {
        mockWebServer.enqueueResponse("login-info-null-200.json", 200)
        val expect = ErrorsString.USER_NULL

        runBlocking {
            aut.login(
                notificationId = "",
                countryCode = "+98",
                phoneNo = "09353588937",
                otpCode = "6804"
            )
                .collect { baseResult ->
                    if (baseResult is BaseResult.GeneralError) {
                        assertThat(baseResult.redId).isEqualTo(expect)
                    } else {
                        Assert.fail("** Result Not Success **")
                    }
                }
        }
    }

    @Test
    fun login_complete_onTestApi() {
        mockWebServer.enqueueResponse("login-200.json", 200)
        val responseBody = mockWebServer.dispatcher.peek()
            .getBody()?.readString(StandardCharsets.UTF_8)
            ?.convertJsonToModel(LoginResponse::class.java)
        if (responseBody?.info == null)
            Assert.fail("** Info Is Null **")
        val expect = responseBody?.info?.toUserEntity()?.toUserModel()
        val userExpect = responseBody?.info?.toUserEntity()


        runBlocking {
            aut.login(
                notificationId = "",
                countryCode = "+98",
                phoneNo = "09353588937",
                otpCode = "6804"
            )
                .collect { baseResult ->
                    assertThat(responseBody?.accessToken)
                        .isEqualTo(
                            shPref.getToken()
                        )
                    assertThat(responseBody?.refreshToken)
                        .isEqualTo(
                            shPref.getRefreshToken()
                        )
                    val user = database.userDao.getUser()
                    if (user.isNotEmpty()) {
                        assertThat(user[0])
                            .isEqualTo(
                                userExpect
                            )
                    } else {
                        Assert.fail("**User not Insert to Database**")
                    }
                    if (baseResult is BaseResult.Success && expect != null) {
                        assertThat(baseResult.value).isEqualTo(expect)
                    } else {
                        Assert.fail("** Result Not Success **")
                    }
                }
        }
    }
}