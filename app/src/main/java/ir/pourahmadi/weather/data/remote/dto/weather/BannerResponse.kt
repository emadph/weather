package ir.pourahmadi.weather.data.remote.dto.Wearher

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.Wearher.BannerModel

class BannerResponse(
    @SerializedName("WearherId") var WearherId: Int? = 0,
    @SerializedName("webLink") var webLink: String? = null,
    @SerializedName("position") var position: Int? = null,
    @SerializedName("imageUrl") var imageUrl: String? = null,
    @SerializedName("bannerSize") var bannerSize: String? = null,
    @SerializedName("isMedia") var isMedia: Boolean? = null,
    @SerializedName("isVocalStream") var isVocalStream: Boolean? = null,
    @SerializedName("isVideoStream") var isVideoStream: Boolean? = null,
) : BaseWearherResponse() {

    fun toBannerModel(): BannerModel {
        val obj = BannerModel()
        obj.WearherId = WearherId
        obj.webLink = webLink
        obj.position = position
        obj.imageUrl = imageUrl
        obj.bannerSize = bannerSize
        obj.isMedia = isMedia
        obj.isVocalStream = isVocalStream
        obj.isVideoStream = isVideoStream
        obj.id = id
        obj.topicId = topicId
        obj.title = title
        obj.publishDateFa = publishDateFa
        obj.topicTitle = topicTitle
        return obj
    }
}