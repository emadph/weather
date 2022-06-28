package ir.pourahmadi.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherListCacheEntity(
    @PrimaryKey
    var id: Int,
    var cityName: String
)
