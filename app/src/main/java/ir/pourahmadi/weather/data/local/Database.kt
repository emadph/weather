package ir.pourahmadi.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.pourahmadi.weather.data.local.dao.WeatherDao
import ir.pourahmadi.weather.data.local.entity.WeatherEntity

@Database(
    entities = [
        WeatherEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val WeatherDao: WeatherDao
}