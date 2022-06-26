package ir.pourahmadi.weather.data.local.dao

import androidx.room.*
import ir.pourahmadi.weather.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM UserEntity WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("DELETE FROM UserEntity")
    suspend fun dropUser()

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity ")
    suspend fun getUser(): List<UserEntity>
}