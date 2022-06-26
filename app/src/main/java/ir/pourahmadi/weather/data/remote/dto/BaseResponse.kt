package ir.pourahmadi.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("m") var m: String = "",
    @SerializedName("error") var error: String = "",
    var errCode: Int? = null
)
