package ir.pourahmadi.weather.domain.use_case

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.data.remote.dto.login.OtpRequest
import ir.pourahmadi.weather.data.repository.FakeLoginRepository
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.utils.Validate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginUseCaseTest {

    private lateinit var loginUsecase: LoginUseCase

    @Before
    fun setup() {
        loginUsecase = LoginUseCase(FakeLoginRepository(), Validate())
    }

    @Test
    fun sendOtp_true() = runBlocking {
        val otpRequest = OtpRequest(
            phoneNumber = "09353588937",
            countryCode = "+98"
        )
        val result = loginUsecase.sendOtp(otpRequest).first()

        assertThat(result is BaseResult.Success).isTrue()
    }

    @Test
    fun login_true() = runBlocking {
        val result = loginUsecase.login(
            notificationId = "",
            countryCode = "+98",
            phoneNo = "09353588937",
            otpCode = "1423"
        ).first()

        assertThat(result is BaseResult.Success).isTrue()
    }
}