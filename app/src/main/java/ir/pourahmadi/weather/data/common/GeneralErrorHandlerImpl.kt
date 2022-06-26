package ir.pourahmadi.weather.data.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.pourahmadi.weather.data.remote.dto.BaseResponse
import ir.pourahmadi.weather.domain.common.error.ErrorEntity
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import javax.inject.Inject

class GeneralErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is UnknownHostException -> ErrorEntity.Network()
            is IOException -> ErrorEntity.Unknown()
            is HttpException -> {
                val errorResponse = convertErrorBody(throwable)
                if (errorResponse != null)
                    ErrorEntity.FromServer(errorResponse)
                else {
                    when (throwable.code()) {
                        // not found
                        HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound()

                        // access denied
                        HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied()

                        // unavailable service
                        HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable()

                        // all the others will be treated as unknown error
                        else -> ErrorEntity.Unknown()
                    }
                }
            }
            else -> ErrorEntity.Unknown()
        }
    }
}

private fun convertErrorBody(throwable: HttpException): BaseResponse? {
    return try {
        throwable.response()?.let { response ->
            response.errorBody()?.let {
                val type = object : TypeToken<BaseResponse>() {}.type
                val err: BaseResponse =
                    Gson().fromJson(it.charStream(), type)
                err.errCode = response.code()
                err
            }
        }
    } catch (exception: Exception) {
        null
    }
}