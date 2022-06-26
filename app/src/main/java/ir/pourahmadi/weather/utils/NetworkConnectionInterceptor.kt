package ir.pourahmadi.weather.utils

import android.content.Context
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.utils.NetworkUtils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!isNetworkAvailable(context)) {
                throw NoConnectionException()
            }
        } catch (e: Exception) {
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message: String
            get() = super.message ?: getStringResources(context, R.string.msg_no_Internet)
    }
}