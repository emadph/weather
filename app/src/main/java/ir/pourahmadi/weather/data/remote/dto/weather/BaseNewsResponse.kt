package ir.pourahmadi.weather.data.remote.dto.Wearher

open class BaseWearherResponse(
    open var desc: String? = null,
    open var id: Int = 0,
    open var publishDate: String? = null,
    open var publishDateFa: String? = null,
    open var thumb: String? = null,
    open var title: String? = null,
    open var topicTitle: String? = null,
    open var imagesCount: Int? = null,
    open var topicId: Int? = null,
)