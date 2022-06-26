package ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder

import ir.pourahmadi.weather.domain.model.Wearher.GalleryModel
import ir.pourahmadi.weather.presentation.components.Wearher.imageGallery.CustomItemWearherGallery
import ir.pourahmadi.weather.presentation.ui.base.BaseViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherClickListener
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherTypeClick

class GalleryWearherViewHolder(
    private val itemViews: CustomItemWearherGallery,
    private val mListener: WearherClickListener
) : BaseViewHolder<GalleryModel>(itemViews) {

    override fun bind(item: GalleryModel?, position: Int) {
        item?.let {
            itemViews.apply {
                setCategoryTitleText(item.topicTitle)
                setDateText(item.publishDateFa)
                setTitleText(item.title)
                item.images?.let { it1 -> setImages(it1) }

                setOnImageFullSizeClick()
                setOnMoreClick()
                moreClickListener = {
                    mListener.onItemClickGallery(WearherTypeClick.onItemClickMore(item))
                }
                imageFullSizeClickListener = {
                    mListener.onItemClickGallery(WearherTypeClick.onItemClickFullSize(item))
                }

                setBottomArea().apply {
                    setBookmark(false)
                    setLike(false)
                    setCommentText("390 k")
                    setViewText("40 k")
                    setLikeText("80 k")

                    setOnLikeClick()
                    setOnBookmarkClick()

                    likeClicklistener = {
                        mListener.onItemClickGallery(WearherTypeClick.onItemClickLike(item))
                    }
                    bookmarkClicklistener = {
                        mListener.onItemClickGallery(WearherTypeClick.onItemClickBookmark(item))
                    }

                }


                setOnClickListener {
                    mListener.onItemClickGallery(WearherTypeClick.onItemClick(item))
                }

            }
        }
    }

}