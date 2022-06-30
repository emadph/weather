package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.weather.LocationInfoModel

class LocationInfoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("country") var country: String,
    @SerializedName("sunrise") var sunrise: String,
    @SerializedName("sunset") var sunset: String
) {

    fun toModel(): LocationInfoModel {
        return LocationInfoModel(country, sunrise,sunset)
    }
}