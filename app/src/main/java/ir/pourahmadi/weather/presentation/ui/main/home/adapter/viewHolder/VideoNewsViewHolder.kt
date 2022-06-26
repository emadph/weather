package ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder

import ir.pourahmadi.weather.domain.model.Wearher.VideoModel
import ir.pourahmadi.weather.presentation.common.ImageWearherType
import ir.pourahmadi.weather.presentation.components.Wearher.CustomItemWearherVideo
import ir.pourahmadi.weather.presentation.ui.base.BaseViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherClickListener
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherTypeClick

class VideoWearherViewHolder(
    private val itemViews: CustomItemWearherVideo,
    private val mListener: WearherClickListener

) : BaseViewHolder<VideoModel>(itemViews) {

    override fun bind(item: VideoModel?, position: Int) {
        item?.let {
            itemViews.apply {
                setCategoryTitleText(item.topicTitle)
                setDateText(item.publishDateFa)
                setTitleText(item.title)
                setDescText(item.desc)
                setImage(item.thumb.toString())
                setImageType(ImageWearherType.video.name)

                setOnMoreClick()
                moreClickListener = {
                    mListener.onItemClickVideo(WearherTypeClick.onItemClickMore(item))
                }

                setBottomArea().apply {
                    setBookmark(true)
                    setLike(false)
                    setCommentText("190 k")
                    setViewText("10 k")
                    setLikeText("199")

                    setOnLikeClick()
                    setOnBookmarkClick()

                    likeClicklistener = {
                        mListener.onItemClickVideo(WearherTypeClick.onItemClickLike(item))
                    }
                    bookmarkClicklistener = {
                        mListener.onItemClickVideo(WearherTypeClick.onItemClickBookmark(item))
                    }


                }
                setOnClickListener {
                    mListener.onItemClickVideo(WearherTypeClick.onItemClick(item))
                }

            }
        }

    }

}