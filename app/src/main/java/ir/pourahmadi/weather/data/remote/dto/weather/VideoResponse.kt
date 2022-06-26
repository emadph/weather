package ir.pourahmadi.weather.data.remote.dto.Wearher

import ir.pourahmadi.weather.domain.model.Wearher.VideoModel

class VideoResponse(
    private val streamUrl: String?,
    private val downloadUrl: String?
) : BaseWearherResponse() {

    fun toVideoModel(): VideoModel {
        val obj = VideoModel(
            streamUrl = streamUrl,
            downloadUrl = downloadUrl
        )
        obj.id = id
        obj.topicId = topicId
        obj.desc = desc
        obj.imagesCount = imagesCount
        obj.publishDate = publishDate
        obj.thumb = thumb
        obj.title = title
        obj.topicTitle = topicTitle
        return obj
    }
}