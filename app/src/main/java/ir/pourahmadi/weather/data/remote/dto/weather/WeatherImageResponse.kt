package ir.pourahmadi.weather.data.remote.dto.Wearher

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.Wearher.WearherImageModel

data class WearherImageResponse(
    @SerializedName("url") var url: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("id") var id: Int = 0
) {
    fun toWearherImageModel(): WearherImageModel {
        return WearherImageModel(
            url, title, id
        )
    }

}
