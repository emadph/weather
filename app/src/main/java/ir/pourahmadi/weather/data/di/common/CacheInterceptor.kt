package ir.pourahmadi.weather.data.di.common

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val originalResponse: Response = chain.proceed(request)
        val cacheControl: String? = originalResponse.header("Cache-Control")
        if (cacheControl == null
            || cacheControl.contains("no-store")
            || cacheControl.contains("no-cache")
            || cacheControl.contains("must-revalidate")
            || cacheControl.contains("max-stale=0")
        ) {
            val cc = CacheControl.Builder()
                .maxStale(1, TimeUnit.MINUTES)
                .maxAge(1, TimeUnit.DAYS)
                .build()
            request = request.newBuilder()
                .removeHeader("Pragma")
                .cacheControl(cc)
                .build()
            return chain.proceed(request)
        } else {
            return originalResponse
        }
    }
}