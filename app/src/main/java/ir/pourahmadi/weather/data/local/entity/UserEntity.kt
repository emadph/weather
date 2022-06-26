package ir.pourahmadi.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.pourahmadi.weather.domain.model.login.UserModel

@Entity
data class UserEntity(
    @PrimaryKey
    var id: Int? = null,
    var publicName: String? = null,
    var fullName: String? = null,
    var bio: String? = null,
    var sex: String? = null,
    var birthdatePersian: String? = null,
    var provinceId: Int? = null,
    var latitudes: String? = null,
    var longitudes: String? = null,
    var avatar: String? = null,
    var userAge: String? = null
) {
    fun toUserModel(): UserModel {
        return UserModel(
            id = id,
            publicName = publicName,
            fullName = fullName,
            bio = bio,
            sex = sex,
            birthdatePersian = birthdatePersian,
            provinceId = provinceId,
            latitudes = latitudes,
            longitudes = longitudes,
            avatar = avatar,
            userAge = userAge
        )
    }
}
