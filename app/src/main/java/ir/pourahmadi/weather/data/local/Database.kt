package ir.pourahmadi.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.pourahmadi.weather.data.local.dao.WeatherDao
import ir.pourahmadi.weather.data.local.entity.HomeTopicsEntity
import ir.pourahmadi.weather.data.local.entity.WeatherListCacheEntity

@Database(
    entities = [
        HomeTopicsEntity::class,
        WeatherListCacheEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val WeatherDao: WeatherDao
}