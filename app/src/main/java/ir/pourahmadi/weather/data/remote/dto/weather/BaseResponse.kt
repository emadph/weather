package ir.pourahmadi.weather.data.remote.dto.weather

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("message") var message: String = "",
    @SerializedName("cod") var error: String = "",
    var errCode: Int? = null
)
