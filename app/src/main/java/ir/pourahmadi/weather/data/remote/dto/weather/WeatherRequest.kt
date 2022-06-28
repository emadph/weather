package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.utils.filterNotNullValues
import ir.pourahmadi.weather.BuildConfig
import ir.pourahmadi.weather.domain.common.ARG_UNII_TEMP

data class WeatherRequest(
    @SerializedName("lat") var lat: String,
    @SerializedName("lon") var lng: String,
    @SerializedName("q") var cityName: String? = null
) {
    fun toRequest(): Map<String, String> {
        val request: Map<String, String?> = mapOf(
            "lat" to lat,
            "lon" to lng,
            "appid" to BuildConfig.SECRET_KEY,
            "units" to ARG_UNII_TEMP,
            "q" to cityName
        )
        return request.filterNotNullValues()
    }

}
