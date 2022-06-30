package ir.pourahmadi.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.pourahmadi.weather.data.remote.dto.weather.CoordinationResponse
import ir.pourahmadi.weather.data.remote.dto.weather.MainWeatherInfoResponse
import ir.pourahmadi.weather.data.remote.dto.weather.WeatherInfoResponse
import ir.pourahmadi.weather.domain.model.weather.WeatherModel

@Entity
data class WeatherEntity(
    @PrimaryKey
    var id: Int,
    var cityName: String,
    var cityLng: String,
    var cityLat: String,
    var temperature: String,
    var maxTemperature: String,
    var minTemperature: String,
    var weatherParametersType: String,
    var weatherIcon: String
){
    fun toWeatherOfflineModel(): WeatherModel {

        val weatherInfo = WeatherInfoResponse(0,weatherParametersType,"",weatherIcon)
        val weatherInfoList = listOf(weatherInfo)

        val coordination = CoordinationResponse(cityLat.toDouble(),cityLng.toDouble())
        val mainWeatherInfoResponse = MainWeatherInfoResponse(temperature.toDouble(), 0.0,minTemperature.toDouble(),maxTemperature.toDouble(),0.0,0.0)

        val weatherInfoListModel = weatherInfoList.map { it.toModel() }

        return WeatherModel(
            coordination.toModel(),
            weatherInfoListModel,
            mainWeatherInfoResponse.toModel(),
           null,
            cityName,
            id
        )
    }
}
