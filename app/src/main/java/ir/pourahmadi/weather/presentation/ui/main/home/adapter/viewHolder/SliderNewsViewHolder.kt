package ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder

import ir.pourahmadi.weather.domain.model.Wearher.BannerModel
import ir.pourahmadi.weather.presentation.components.Wearher.imageSlider.CustomItemWearherSlider
import ir.pourahmadi.weather.presentation.ui.base.BaseViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherClickListener

class SliderWearherViewHolder(
    private val itemViews: CustomItemWearherSlider,
    private val mListener: WearherClickListener
) : BaseViewHolder<List<BannerModel>?>(itemViews) {

    override fun bind(item: List<BannerModel>?, position: Int) {
        item?.let {
            itemViews.apply {
                setImages(item, mListener)
                setOnClickListener {
                }

            }
        }
    }

}