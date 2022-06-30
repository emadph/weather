package ir.pourahmadi.weather.domain.common.error

import ir.pourahmadi.weather.data.remote.dto.weather.BaseResponse

sealed class ErrorEntity {

    data class FromServer(val error: BaseResponse? = null) : ErrorEntity()

    data class Network(val redId: Int = ErrorsString.NETWORK_CONNECTION_FAIL) : ErrorEntity()

    data class NotFound(val redId: Int = ErrorsString.NETWORK_FAIL) : ErrorEntity()

    data class AccessDenied(val redId: Int = ErrorsString.NETWORK_FAIL) : ErrorEntity()

    data class ServiceUnavailable(val redId: Int = ErrorsString.NETWORK_CONNECTION_FAIL) :
        ErrorEntity()

    data class Unknown(val redId: Int = ErrorsString.NETWORK_FAIL) : ErrorEntity()
}