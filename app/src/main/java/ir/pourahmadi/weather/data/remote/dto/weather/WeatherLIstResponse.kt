package ir.pourahmadi.weather.data.remote.dto.Wearher

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.Wearher.BannerModel
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel

data class WearherLIstResponse(
    @SerializedName("simple") var simple: SimpleResponse? = null,
    @SerializedName("video") var video: VideoResponse? = null,
    @SerializedName("gallery") var gallery: GalleryResponse? = null,
    @SerializedName("banner") var banner: List<BannerResponse>? = null,
) {
    private fun toBannerModelList(): List<BannerModel>? {
        return banner?.map { it.toBannerModel() }
    }

    fun toWearherListModel(): WearherListModel {
        return WearherListModel(
            simpleModel = simple?.toSimpleModel(),
            videoModel = video?.toVideoModel(),
            galleryModel = gallery?.toGalleryModel(),
            bannerModel = toBannerModelList(),
        )
    }
}