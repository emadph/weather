package ir.pourahmadi.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.pourahmadi.weather.data.local.entity.WeatherListCacheEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherListCashe(WeatherList: WeatherListCacheEntity)

    @Query("DELETE FROM WeatherListCacheEntity WHERE id = :id")
    suspend fun deleteWeatherCasheById(id: Int)

    @Query("DELETE FROM WeatherListCacheEntity")
    suspend fun dropWeatherListCashe()

    @Query("SELECT * FROM WeatherListCacheEntity WHERE cityName = :cityName ")
    suspend fun getWeatherListCache(cityName: String): WeatherListCacheEntity?

}