package ir.pourahmadi.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.pourahmadi.weather.data.local.entity.HomeTopicsEntity
import ir.pourahmadi.weather.data.local.entity.TopicEntity
import ir.pourahmadi.weather.data.local.relation.HomeTopicList
import ir.pourahmadi.weather.data.local.relation.TopicList

@Dao
interface CommonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: TopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(topics: List<TopicEntity>)

    @Query("SELECT * FROM TopicEntity ")
    suspend fun getTopicsAndSub(): List<TopicList>

    @Query("SELECT * FROM TopicEntity ")
    suspend fun getTopics(): List<TopicEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeTopic(topic: List<HomeTopicsEntity>)

    @Query("SELECT * FROM HomeTopicsEntity ")
    suspend fun getHomeTopicsAndSub(): List<HomeTopicList>

    @Query("SELECT * FROM HomeTopicsEntity ")
    suspend fun getHomeTopics(): List<HomeTopicsEntity>
}