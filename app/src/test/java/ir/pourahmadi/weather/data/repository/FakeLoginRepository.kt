package ir.pourahmadi.weather.data.repository

import ir.pourahmadi.weather.data.remote.dto.login.OtpRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.model.login.OtpModel
import ir.pourahmadi.weather.domain.model.login.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLoginRepository : LoginRepository {

    override suspend fun sendOtp(otpRequest: OtpRequest): Flow<BaseResult<OtpModel>> {
        return flow {
            val mModel = OtpModel(
                mobile = "09353588937",
                otpTimeout = "60"
            )
            emit(BaseResult.Success(mModel))
        }
    }

    override suspend fun login(
        notificationId: String?,
        countryCode: String,
        phoneNo: String,
        otpCode: String
    ): Flow<BaseResult<UserModel>> {
        return flow {
            val mModel = UserModel(
                publicName = "publicName",
                fullName = "fullName",
                bio = "bio",
                sex = "sex",
                birthdatePersian = "1399/11/21",
                provinceId = 1,
                latitudes = "21.21456",
                longitudes = "14.5674",
                avatar = "avatar",
                userAge = "123456"
            )
            emit(BaseResult.Success(mModel))
        }

    }
}