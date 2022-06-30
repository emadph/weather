package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.weather.WeatherModel

data class WeatherBaseResponse(
    @SerializedName("coord") var coordination: CoordinationResponse? = null,
    @SerializedName("weather") var weatherInfoCodes: List<WeatherInfoResponse>? = null,
    @SerializedName("main") var mainWeatherInfo: MainWeatherInfoResponse? = null,
    @SerializedName("sys") var locationInfo: LocationInfoResponse? = null,
    @SerializedName("name") var cityName: String? = null,
    @SerializedName("id") var id: Int
) {
    fun toWeatherModel(): WeatherModel {
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
    fun toWeatherOfflineModel(  id: Int,
                                cityName: String,
                                cityLng: String,
                                cityLat: String,
                                temperature: String,
                                maxTemperature: String,
                                minTemperature: String,
                                weatherParametersType: String,
                                weatherIcon: String
                               ): WeatherModel {

        val weatherInfo = WeatherInfoResponse(0,weatherParametersType,"",weatherIcon)
        val weatherInfoList = listOf(weatherInfo)

        val coordination = CoordinationResponse(cityLat.toDouble(),cityLng.toDouble())
        val mainWeatherInfoResponse = MainWeatherInfoResponse(temperature.toDouble(), 0.0,minTemperature.toDouble(),maxTemperature.toDouble(),0.0,0.0)

        return WeatherModel(
            coordination,
            weatherInfoList,
            mainWeatherInfoResponse,
            cityName,
            id
        )
    }

    private fun WeatherModel(
        coordinationModel: CoordinationResponse,
        weatherInfo: List<WeatherInfoResponse>,
        mainWeatherInfo: MainWeatherInfoResponse,
        cityName: String,
        id: Int
    ): WeatherModel {
        return WeatherModel(
            coordinationModel,
            weatherInfo,
            mainWeatherInfo,
            cityName,
            id
        )
    }
}