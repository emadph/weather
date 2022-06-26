package ir.pourahmadi.weather.presentation.ui.main.home.adapter

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.pourahmadi.weather.domain.model.Wearher.WearherListModel
import ir.pourahmadi.weather.presentation.common.Wearher
import ir.pourahmadi.weather.presentation.components.Wearher.CustomItemWearherSimple
import ir.pourahmadi.weather.presentation.components.Wearher.CustomItemWearherVideo
import ir.pourahmadi.weather.presentation.components.Wearher.imageGallery.CustomItemWearherGallery
import ir.pourahmadi.weather.presentation.components.Wearher.imageSlider.CustomItemWearherSlider
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder.GalleryWearherViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder.SimpleWearherViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder.SliderWearherViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.WearherViewHolder.VideoWearherViewHolder
import ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks.WearherClickListener


class WearherListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mListener: WearherClickListener
    private val differCallback = object : DiffUtil.ItemCallback<WearherListModel>() {
        override fun areItemsTheSame(oldItem: WearherListModel, newItem: WearherListModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WearherListModel, newItem: WearherListModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    fun setListener(listener: WearherClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        val data = differ.currentList[position]

        when (holder) {
            is SimpleWearherViewHolder -> {
                holder.bind(data.simpleModel, position)
            }
            is GalleryWearherViewHolder -> {
                holder.bind(data.galleryModel, position)
            }
            is VideoWearherViewHolder -> {
                holder.bind(data.videoModel, position)
            }
            is SliderWearherViewHolder -> {
                holder.bind(data.bannerModel, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val data = differ.currentList[position]

        return when {
            data.simpleModel != null -> {
                Wearher.Wearher_SIMPLE.type
            }
            data.videoModel != null -> {
                Wearher.Wearher_VIDEO.type
            }
            data.galleryModel != null -> {
                Wearher.Wearher_GALLERY.type
            }
            data.bannerModel != null -> {
                Wearher.Wearher_SLIDER.type
            }
            else -> {
                Wearher.Wearher_SIMPLE.type
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Wearher.Wearher_SIMPLE.type -> {
                val view = CustomItemWearherSimple(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return SimpleWearherViewHolder(view, mListener)
            }
            Wearher.Wearher_VIDEO.type -> {
                val view = CustomItemWearherVideo(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return VideoWearherViewHolder(view, mListener)
            }

            Wearher.Wearher_GALLERY.type -> {
                val view = CustomItemWearherGallery(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                return GalleryWearherViewHolder(view, mListener)
            }
            Wearher.Wearher_SLIDER.type -> {
                val view = CustomItemWearherSlider(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                return SliderWearherViewHolder(view, mListener)
            }

            else -> {
                val view = CustomItemWearherSimple(parent.context)
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                return SimpleWearherViewHolder(view, mListener)
            }
        }
    }


}