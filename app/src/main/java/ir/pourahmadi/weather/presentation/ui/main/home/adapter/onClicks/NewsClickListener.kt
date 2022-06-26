package ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks

import ir.pourahmadi.weather.domain.model.Wearher.BannerModel
import ir.pourahmadi.weather.domain.model.Wearher.GalleryModel
import ir.pourahmadi.weather.domain.model.Wearher.SimpleModel
import ir.pourahmadi.weather.domain.model.Wearher.VideoModel

interface WearherClickListener {
    fun onItemClickSimple(click: WearherTypeClick<SimpleModel>)
    fun onItemClickGallery(click: WearherTypeClick<GalleryModel>)
    fun onItemClickSlider(click: WearherTypeClick<BannerModel>)
    fun onItemClickVideo(click: WearherTypeClick<VideoModel>)
}