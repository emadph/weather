package ir.pourahmadi.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.pourahmadi.weather.data.local.dao.CommonDao
import ir.pourahmadi.weather.data.local.dao.WearherDao
import ir.pourahmadi.weather.data.local.dao.UserDao
import ir.pourahmadi.weather.data.local.entity.HomeTopicsEntity
import ir.pourahmadi.weather.data.local.entity.WearherListCacheEntity
import ir.pourahmadi.weather.data.local.entity.TopicEntity
import ir.pourahmadi.weather.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TopicEntity::class,
        HomeTopicsEntity::class,
        WearherListCacheEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val commonDao: CommonDao
    abstract val WearherDao: WearherDao
}