package ir.pourahmadi.weather.data.repository

import ir.pourahmadi.weather.data.remote.dto.profile.InfoRequest
import ir.pourahmadi.weather.domain.common.base.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProfileRepository : ProfileRepository {

    override suspend fun checkPublicName(publicName: String): Flow<BaseResult<Unit>> {
        return flow {
            emit(BaseResult.Success())
        }
    }

    override suspend fun saveProfile(infoRequest: InfoRequest): Flow<BaseResult<Unit>> {
        return flow {
            emit(BaseResult.Success())
        }
    }
}