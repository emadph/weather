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

            val showCenterLogo = typedArray.getBoolean(
                R.styleable.CustomToolbar_showCenterLogo,
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


            when (showCenterLogo) {
                true -> {
                    imgToolbarHeaderLogo.visibility = VISIBLE
                    imgToolbarHeaderLogo.setImageDrawable(drawableCenterLogo)
                }
                false -> {
                    imgToolbarHeaderLogo.visibility = GONE
                }
            }

            typedArray.recycle()
        }
    }

}