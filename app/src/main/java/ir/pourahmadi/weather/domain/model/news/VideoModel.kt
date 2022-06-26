package ir.pourahmadi.weather.domain.model.Wearher


data class VideoModel(
    val streamUrl: String?,
    val downloadUrl: String?
) : BaseWearherModel()