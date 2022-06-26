package ir.pourahmadi.weather.domain.model.Wearher

data class GalleryModel(
    var images: List<WearherImageModel>? = null,
) : BaseWearherModel()