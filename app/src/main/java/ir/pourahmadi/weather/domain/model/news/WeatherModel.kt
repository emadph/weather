package ir.pourahmadi.weather.domain.model.news

data class WeatherModel(
    var coordinationModel: CoordinateModel? = null,
    var weatherInfo: List<WeatherInfoModel>? = null,
    var mainWeatherInfo: MainWeatherInfoModel? = null,
    var locationInfo: LocationInfoModel? = null,
    var cityName: String? = null,
    var id: Int
)


