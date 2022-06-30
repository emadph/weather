package ir.pourahmadi.weather.domain.model.weather


data class WeatherInfoModel(
    val id: Int,
    val weatherParametersType: String,
    val weatherConditionGroup: String,
    val weatherIcon: String
)