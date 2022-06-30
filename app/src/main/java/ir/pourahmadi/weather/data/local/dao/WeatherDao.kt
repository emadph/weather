package ir.pourahmadi.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.pourahmadi.weather.data.local.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert()
    suspend fun insertWeatherData(entity: WeatherEntity)

    @Query("DELETE FROM WeatherEntity")
    suspend fun dropWeatherData()

    @Query("SELECT * FROM WeatherEntity")
    suspend fun getWeather( ): WeatherEntity?

}