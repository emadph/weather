package ir.pourahmadi.weather.data.repository.login

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.data.remote.api.LoginApi
import ir.pourahmadi.weather.data.remote.dto.login.OtpRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.utils.SharedPrefs
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
class LoginRepositoryRealApiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("SharedPrefs")
    lateinit var shPref: SharedPrefs

    @Inject
    @Named("OkHttpClient")
    lateinit var client: OkHttpClient

    @Inject
    @Named("LoginApi")
    lateinit var api: LoginApi

    @Inject
    @Named("ErrorHandler")
    lateinit var errorHandler: ErrorHandler

    @Inject
    @Named("LoginRepository")
    lateinit var aut: LoginRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun sendOtp_onTestApi_true() = runBlocking {
        val otpRequest = OtpRequest(
            phoneNumber = "09353588937",
            countryCode = "+98"
        )
        aut.sendOtp(otpRequest)
            .collect { baseResult ->
                assertThat(baseResult is BaseResult.Success).isTrue()
            }
    }
}