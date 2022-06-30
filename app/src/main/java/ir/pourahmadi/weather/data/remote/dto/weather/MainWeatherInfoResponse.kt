package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.weather.MainWeatherInfoModel

class MainWeatherInfoResponse(
    @SerializedName("temp") var temp: Double,
    @SerializedName("feels_like") var feelsLike: Double,
    @SerializedName("temp_min") var temp_min: Double,
    @SerializedName("temp_max") var temp_max: Double,
    @SerializedName("pressure") var pressure: Double,
    @SerializedName("humidity") var humidity: Double
) {

    fun toModel(): MainWeatherInfoModel {
        return MainWeatherInfoModel(temp, feelsLike,pressure,humidity,temp_min,temp_max)
    }
}