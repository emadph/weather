package ir.pourahmadi.weather.domain.common.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}