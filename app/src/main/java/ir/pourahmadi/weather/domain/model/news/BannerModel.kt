package ir.pourahmadi.weather.domain.model.Wearher

data class BannerModel(
    var WearherId: Int? = null,
    var webLink: String? = null,
    var position: Int? = null,
    var imageUrl: String? = null,
    var bannerSize: String? = null,
    var isMedia: Boolean? = null,
    var isVocalStream: Boolean? = null,
    var isVideoStream: Boolean? = null
) : BaseWearherModel()