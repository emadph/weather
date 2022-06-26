package ir.pourahmadi.weather.presentation.ui.main.category.sheetComponent

import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.components.BaseBottomSheetDialogFragment
import ir.pourahmadi.weather.utils.getStringResources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_sheet_more_Wearher.*

@AndroidEntryPoint
class WearherMoreBottomSheet : BaseBottomSheetDialogFragment() {

    private var topicTitle: String = ""

    override fun getLayoutId(): Int {
        return R.layout.layout_sheet_more_Wearher
    }

    override fun beforeView() {
    }

    override fun afterView() {
        shareViewModel.WearherModel.observe(viewLifecycleOwner) { data ->
            when {
                data.simpleModel != null -> {
                    topicTitle = data.simpleModel?.topicTitle.toString()
                }
                data.videoModel != null -> {
                    topicTitle = data.videoModel?.topicTitle.toString()
                }
                data.galleryModel != null -> {
                    topicTitle = data.galleryModel?.topicTitle.toString()
                }
            }

            tvBSMoreWearherGoToCategory.text =
                getStringResources(context!!, R.string.lbl_CategoryGo).plus(" ")
                    .plus(topicTitle)
        }

        onClicks()

    }

    override fun destroyView() {
    }


    fun onClicks() {
        tvBSMoreWearherSave.setOnClickListener {
            dismiss()
        }
        tvBSMoreWearherShare.setOnClickListener {
            dismiss()
        }
        tvBSMoreWearherGoToCategory.setOnClickListener {
            dismiss()
        }
        tvBSMoreWearherReport.setOnClickListener {
            dismiss()
        }

    }
}