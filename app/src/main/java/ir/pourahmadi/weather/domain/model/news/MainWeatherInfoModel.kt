package ir.pourahmadi.weather.domain.model.news

data class MainWeatherInfoModel(
    val temperature: Long,
    val humanTemperature: Long,
    val atmosphericPressure : Long,
    val humidity: Long,
    val minTemperature: Long,
    val maxTemperature: Long
)