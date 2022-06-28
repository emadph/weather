package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.news.WeatherModel

data class WeatherBaseResponse(
    @SerializedName("coord") var coordination: CoordinationResponse? = null,
    @SerializedName("weather") var weatherInfoCodes: List<WeatherInfoResponse>? = null,
    @SerializedName("main") var mainWeatherInfo: MainWeatherInfoResponse? = null,
    @SerializedName("sys") var locationInfo: LocationInfoResponse? = null,
    @SerializedName("name") var cityName: String? = null,
    @SerializedName("id") var id: Int
) {
    fun toWeatherListModel(): WeatherModel {
        val weatherInfoList = weatherInfoCodes?.map { it.toModel() }
        return WeatherModel(
            coordination?.toModel(),
            weatherInfoList,
            mainWeatherInfo?.toModel(),
            locationInfo?.toModel(),
            cityName,
            id
        )
    }
}