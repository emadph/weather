package ir.pourahmadi.weather.data.di.common

import ir.pourahmadi.weather.utils.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor  : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .build()
        return chain.proceed(newRequest)
    }
}