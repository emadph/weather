package ir.pourahmadi.weather.data.remote.dto.Wearher

import com.google.gson.annotations.SerializedName
import ir.pourahmadi.weather.domain.model.Wearher.GalleryModel

class GalleryResponse(
    @SerializedName("images") var images: List<WearherImageResponse>? = null,
) : BaseWearherResponse() {

    fun toGalleryModel(): GalleryModel {
        val obj = GalleryModel()
        obj.id = id
        obj.topicId = topicId
        obj.desc = desc
        obj.images = images?.map { it.toWearherImageModel() }
        obj.imagesCount = imagesCount
        obj.publishDate = publishDate
        obj.publishDateFa = publishDateFa
        obj.thumb = thumb
        obj.title = title
        obj.topicTitle = topicTitle
        return obj
    }
}