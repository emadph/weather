package ir.pourahmadi.weather.domain.use_case

import com.google.common.truth.Truth
import ir.pourahmadi.weather.data.remote.dto.profile.InfoRequest
import ir.pourahmadi.weather.data.repository.FakeProfileRepository
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.utils.Validate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProfileUseCaseTest {

    private lateinit var profileUseCase: ProfileUseCase

    @Before
    fun setup() {
        profileUseCase = ProfileUseCase(FakeProfileRepository(), Validate())
    }

    @Test
    fun checkPublicName_true() = runBlocking {
        val result = profileUseCase.checkPublicName(
            "fggjh12"
        ).first()
        Truth.assertThat(result is BaseResult.Success).isTrue()
    }

    @Test
    fun saveProfile_true() = runBlocking {
        val result = profileUseCase.saveProfile(
            InfoRequest(publicName = "dsfgsdf34")
        ).first()
        Truth.assertThat(result is BaseResult.Success).isTrue()
    }
}