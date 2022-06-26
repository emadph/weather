package ir.pourahmadi.weather.domain.common.base

import ir.pourahmadi.weather.domain.common.error.ErrorEntity
import ir.pourahmadi.weather.domain.common.error.ErrorsString


sealed class BaseResult<out T> {
    data class Success<out T>(val value: T? = null) : BaseResult<T>()
    data class NetworkError<T>(val error: ErrorEntity) : BaseResult<T>()
    data class GeneralError<T>(val redId: Int = ErrorsString.RESPONSE_NULL) : BaseResult<T>()
}
