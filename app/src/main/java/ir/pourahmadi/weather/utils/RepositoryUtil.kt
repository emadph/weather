package ir.pourahmadi.weather.utils

import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun <T> safeCall(
    errorHandler: ErrorHandler,
    before: (suspend () -> BaseResult<T>)? = null,
    action: suspend () -> BaseResult<T>
): Flow<BaseResult<T>> {
    return flow {
        try {
            if (before != null) {
                emit(before())
            }
            emit(action())
        } catch (throwable: Throwable) {
            emit(
                BaseResult.NetworkError(errorHandler.getError(throwable))
            )
        }
    }
}



