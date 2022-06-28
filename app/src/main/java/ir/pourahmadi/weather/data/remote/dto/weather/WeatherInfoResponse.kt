package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.news.WeatherInfoModel

class WeatherInfoResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("main") var main: String,
    @SerializedName("description") var description: String,
    @SerializedName("icon") var icon: String
) {

    fun toModel(): WeatherInfoModel {
        return WeatherInfoModel(id, main,description,icon)
    }
}