package ir.pourahmadi.weather.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.utils.getDrawable
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.CustomToolbar, 0, 0
            )
            val setCenterText = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.CustomToolbar_setCenterText,
                        R.string.empty
                    )
            )
            val setBigRightText = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.CustomToolbar_setBigRightText,
                        R.string.empty
                    )
            )
            val setRightText = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.CustomToolbar_setRightText,
                        R.string.empty
                    )
            )

            val showBack = typedArray.getBoolean(
                R.styleable.CustomToolbar_showBack,
                false
            )
            val showCenterLogo = typedArray.getBoolean(
                R.styleable.CustomToolbar_showCenterLogo,
                false
            )
            val showIconProfile = typedArray.getBoolean(
                R.styleable.CustomToolbar_showIconProfile,
                false
            )
            val showMenu = typedArray.getBoolean(
                R.styleable.CustomToolbar_showMenu,
                false
            )
            val showMore = typedArray.getBoolean(
                R.styleable.CustomToolbar_showMore,
                false
            )
            val showNotification = typedArray.getBoolean(
                R.styleable.CustomToolbar_showNotification,
                false
            )

            val showSetting = typedArray.getBoolean(
                R.styleable.CustomToolbar_showSetting,
                false
            )
            val showCenterText = typedArray.getBoolean(
                R.styleable.CustomToolbar_showCenterText,
                false
            )
            val showRightText = typedArray.getBoolean(
                R.styleable.CustomToolbar_showRightText,
                false
            )
            val showBigRightText = typedArray.getBoolean(
                R.styleable.CustomToolbar_showBigRightText,
                false
            )

            val drawableCenterLogo = getDrawable(
                context,
                typedArray
                    .getResourceId(
                        R.styleable.CustomToolbar_DrawableLogo,
                        R.drawable.ic_header_logo
                    )
            )

            when (showBack) {
                true -> {
                    imgToolbarBack.visibility = VISIBLE
                }
                false -> {
                    imgToolbarBack.visibility = GONE
                }
            }
            when (showIconProfile) {
                true -> {
                    imgToolbarProfile.visibility = VISIBLE
                }
                false -> {
                    imgToolbarProfile.visibility = GONE
                }
            }

            when (showCenterLogo) {
                true -> {
                    imgToolbarHeaderLogo.visibility = VISIBLE
                    imgToolbarHeaderLogo.setImageDrawable(drawableCenterLogo)
                }
                false -> {
                    imgToolbarHeaderLogo.visibility = GONE
                }
            }

            when (showMenu) {
                true -> {
                    imgToolbarMenu.visibility = VISIBLE
                }
                false -> {
                    imgToolbarMenu.visibility = GONE
                }
            }

            when (showMore) {
                true -> {
                    imgToolbarMore.visibility = VISIBLE
                }
                false -> {
                    imgToolbarMore.visibility = GONE
                }
            }

            when (showSetting) {
                true -> {
                    imgToolbarSetting.visibility = VISIBLE
                }
                false -> {
                    imgToolbarSetting.visibility = GONE
                }
            }

            when (showCenterText) {
                true -> {
                    tvToolbarTitleCenter.visibility = VISIBLE
                }
                false -> {
                    tvToolbarTitleCenter.visibility = GONE
                }
            }
            when (showBigRightText) {
                true -> {
                    tvToolbarTitle.visibility = VISIBLE
                }
                false -> {
                    tvToolbarTitle.visibility = GONE
                }
            }

            when (showRightText) {
                true -> {
                    tvToolbarSubTitle.visibility = VISIBLE
                }
                false -> {
                    tvToolbarSubTitle.visibility = GONE
                }
            }


            when (showNotification) {
                true -> {
                    imgToolbarNotification.visibility = VISIBLE
                }
                false -> {
                    imgToolbarNotification.visibility = GONE
                }

            }

            tvToolbarTitleCenter.text = setCenterText
            tvToolbarTitle.text = setBigRightText
            tvToolbarSubTitle.text = setRightText

            typedArray.recycle()
        }
    }

    fun setRightText(text: String) {
        tvToolbarSubTitle.text = text
    }

    fun setBigRightText(text: String) {
        tvToolbarTitle.text = text
    }

    fun setCenterText(text: String) {
        tvToolbarTitleCenter.text = text
    }

    fun setCenterLogo(url: String) {

    }

    fun haveNotification(haveNotification: Boolean) {
        when (haveNotification) {
            true -> {
                imgToolbarNotification.setImageResource(R.drawable.ic_notification_new)
            }
            false -> {
                imgToolbarNotification.setImageResource(R.drawable.ic_notification)
            }
        }
    }


}