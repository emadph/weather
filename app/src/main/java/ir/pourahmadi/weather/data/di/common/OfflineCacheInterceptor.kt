package ir.pourahmadi.weather.data.di.common

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class OfflineCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(chain.request())
        } catch (e: Exception) {
            val cacheControl = CacheControl.Builder()
                .onlyIfCached()
                .maxStale(1, TimeUnit.MINUTES)
                .maxAge(1, TimeUnit.DAYS)
                .build()
            val offlineRequest: Request = chain.request().newBuilder()
                .cacheControl(cacheControl)
                .removeHeader("Pragma")
                .build()
            chain.proceed(offlineRequest)
        }
    }
}