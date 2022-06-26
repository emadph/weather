package ir.pourahmadi.weather.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.common.gone
import ir.pourahmadi.weather.presentation.common.visible

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val progressBar: LottieAnimationView
    private val buttonTextView: TextView

    init {
        val root = LayoutInflater.from(context).inflate(
            R.layout.progress_button,
            this, true
        )
        buttonTextView = root.findViewById(R.id.button_text)
        progressBar = root.findViewById(R.id.progress_indicator)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        val arr = context.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressButton,
            defStyleAttr,
            0
        )

        val buttonText = arr.getString(R.styleable.ProgressButton_text)
        val loading = arr.getBoolean(R.styleable.ProgressButton_loading, false)
        val enabled = arr.getBoolean(R.styleable.ProgressButton_enabled, true)
        val lottieResId =
            arr.getResourceId(R.styleable.ProgressButton_lottie_resId, R.raw.lf20_0vnbu79m)
        arr.recycle()
        isEnabled = enabled
        buttonTextView.isEnabled = enabled
        setText(buttonText)
        progressBar.setAnimation(lottieResId)
        setLoading(loading)
    }

    fun setLoading(loading: Boolean) {
        isClickable = !loading //Disable clickable when loading
        if (loading) {
            buttonTextView.gone()
            progressBar.visible()
        } else {
            buttonTextView.visible()
            progressBar.gone()
        }
    }

    fun setText(text: String?) {
        buttonTextView.text = text
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        buttonTextView.isEnabled = enabled

        if (enabled)
            buttonTextView.alpha = 1f
        else
            buttonTextView.alpha = 0.5f

    }

}
