package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.news.MainWeatherInfoModel

class MainWeatherInfoResponse(
    @SerializedName("temp") var temp: Long,
    @SerializedName("feels_like") var feelsLike: Long,
    @SerializedName("temp_min") var temp_min: Long,
    @SerializedName("temp_max") var temp_max: Long,
    @SerializedName("pressure") var pressure: Long,
    @SerializedName("humidity") var humidity: Long
) {

    fun toModel(): MainWeatherInfoModel {
        return MainWeatherInfoModel(temp, feelsLike,temp_min,temp_max,pressure,humidity)
    }
}