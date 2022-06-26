package ir.pourahmadi.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WearherListCacheEntity(
    @PrimaryKey
    var url: String,
    var response: String? = null
)
