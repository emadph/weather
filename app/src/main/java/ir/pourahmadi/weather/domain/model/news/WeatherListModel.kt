package ir.pourahmadi.weather.domain.model.Wearher

data class WearherListModel(
    var simpleModel: SimpleModel? = null,
    var videoModel: VideoModel? = null,
    var galleryModel: GalleryModel? = null,
    var bannerModel: List<BannerModel>? = null
)


