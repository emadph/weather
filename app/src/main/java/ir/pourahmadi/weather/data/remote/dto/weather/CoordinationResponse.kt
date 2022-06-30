package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.weather.CoordinateModel

class CoordinationResponse(
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") var lng: Double
) {

    fun toModel(): CoordinateModel {
        return CoordinateModel(lat, lng)
    }
}