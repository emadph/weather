package ir.pourahmadi.weather.data.remote.dto.Wearher

import ir.pourahmadi.weather.domain.model.Wearher.SimpleModel

class SimpleResponse : BaseWearherResponse() {

    fun toSimpleModel(): SimpleModel {
        val simple = SimpleModel()
        simple.id = id
        simple.topicId = topicId
        simple.desc = desc
        simple.imagesCount = imagesCount
        simple.publishDate = publishDate
        simple.publishDateFa = publishDateFa
        simple.thumb = thumb
        simple.title = title
        simple.topicTitle = topicTitle
        return simple
    }
}