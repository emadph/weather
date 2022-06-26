package ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder

import ir.pourahmadi.weather.domain.model.Wearher.SimpleModel
import ir.pourahmadi.weather.presentation.common.ImageWearherType
import ir.pourahmadi.weather.presentation.components.Wearher.CustomItemWearherSimple
import ir.pourahmadi.weather.presentation.ui.base.BaseViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherClickListener
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherTypeClick

class SimpleWearherViewHolder(
    private val itemViews: CustomItemWearherSimple,
    private var onItemClickListenerSimple: WearherClickListener
) : BaseViewHolder<SimpleModel>(itemViews) {

    override fun bind(item: SimpleModel?, position: Int) {
        item?.let {
            itemViews.apply {
                setCategoryTitleText(item.topicTitle)
                setDateText(item.publishDateFa)
                setTitleText(item.title)
                setDescText(item.desc)
                setImage(item.thumb.toString())
                setImageType(ImageWearherType.simple.name)
                setOnMoreClick()
                moreClickListener = {
                    onItemClickListenerSimple.onItemClickSimple(WearherTypeClick.onItemClickMore(item))
                }
                setBottomArea().apply {
                    setBookmark(false)
                    setLike(true)
                    setCommentText("90 k")
                    setViewText("120 k")
                    setLikeText("20 k")

                    setOnLikeClick()
                    setOnBookmarkClick()

                    likeClicklistener = {
                        onItemClickListenerSimple.onItemClickSimple(
                            WearherTypeClick.onItemClickLike(
                                item
                            )
                        )
                    }
                    bookmarkClicklistener = {
                        onItemClickListenerSimple.onItemClickSimple(
                            WearherTypeClick.onItemClickBookmark(
                                item
                            )
                        )
                    }

                }


                setOnClickListener {
                    onItemClickListenerSimple.onItemClickSimple(WearherTypeClick.onItemClick(item))
                }

            }
        }
    }


}