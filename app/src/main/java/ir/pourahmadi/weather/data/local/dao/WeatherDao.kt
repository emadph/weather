package ir.pourahmadi.weather.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.pourahmadi.weather.data.local.entity.WearherListCacheEntity

@Dao
interface WearherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWearherListCashe(WearherList: WearherListCacheEntity)

    @Query("DELETE FROM WearherListCacheEntity WHERE url = :url")
    suspend fun deleteWearherCasheByUrl(url: String)

    @Query("DELETE FROM WearherListCacheEntity")
    suspend fun dropWearherListCashe()

    @Query("SELECT * FROM WearherListCacheEntity WHERE url = :url ")
    suspend fun getWearherListCache(url: String): WearherListCacheEntity?

}