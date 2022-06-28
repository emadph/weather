package ir.pourahmadi.weather.data.common

import ir.pourahmadi.weather.domain.common.error.ErrorEntity
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class GeneralErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is UnknownHostException -> ErrorEntity.Network()
            is IOException -> ErrorEntity.Unknown()
            is HttpException -> {
                val errorResponse = (throwable)
                ErrorEntity.FromServer(errorResponse)
            }
            else -> ErrorEntity.Unknown()
        }
    }
}