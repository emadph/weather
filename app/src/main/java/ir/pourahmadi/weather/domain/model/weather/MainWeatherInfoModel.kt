package ir.pourahmadi.weather.domain.model.weather

data class MainWeatherInfoModel(
    val temperature: Double,
    val humanTemperature: Double,
    val atmosphericPressure : Double,
    val humidity: Double,
    val minTemperature: Double,
    val maxTemperature: Double
)