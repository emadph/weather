package ir.pourahmadi.weather.presentation.components

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.utils.getDrawable
import kotlinx.android.synthetic.main.layout_custom_edittext.view.*


class CustomEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_edittext, this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.CustomEditText, 0, 0
            )
            val setErrorText = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.CustomEditText_setErrorText,
                        R.string.empty
                    )
            )

            val showError = typedArray.getBoolean(
                R.styleable.CustomEditText_showError,
                false
            )


            when (showError) {
                true -> {
                    tvCustomeError.visibility = VISIBLE
                }
                false -> {
                    edtCustome.background = getDrawable(context, R.drawable.shape_otp_view)
                    tvCustomeError.visibility = GONE
                }
            }

            tvCustomeError.text = setErrorText

            typedArray.recycle()
        }
    }


    fun setErrorText(text: String) {
        tvCustomeError.visibility = VISIBLE
        tvCustomeError.text = text
    }

    fun setText(text: String) {
        edtCustome.setText(text)
    }

    fun setSelection(text: String) {
        edtCustome.setSelection(text.length)
    }

    fun getText(): String {
        return edtCustome.text.toString()
    }

    fun showError(isErrorShow: Boolean) {
        edtCustome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        when (isErrorShow) {
            true -> {
                tvCustomeError.visibility = VISIBLE
                edtCustome.background = getDrawable(context, R.drawable.shape_error_view)
            }
            false -> {
                tvCustomeError.visibility = GONE
                edtCustome.background = getDrawable(context, R.drawable.shape_otp_view)
            }
        }
    }

    fun showSuccess(isSuccess: Boolean) {
        when (isSuccess) {
            true -> {
                tvCustomeError.visibility = GONE
                edtCustome.background = getDrawable(context, R.drawable.shape_otp_view)
                edtCustome.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_check,
                    0
                );
            }
            false -> {
                edtCustome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    fun setFocus() {
        edtCustome.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun setLength(length: Int) {
        edtCustome.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(length))
    }

    fun setInputType(type: Int) {
        edtCustome.inputType = type
    }

    fun setImeOptions(type: Int) {
        edtCustome.imeOptions = type
    }

    fun setRawInputType(type: Int) {
        edtCustome.setRawInputType(type)
    }

    fun setCanType(cantype: Boolean) {
        when (cantype) {
            true -> {
                edtCustome.isEnabled = true
                edtCustome.isClickable = true
                edtCustome.isFocusable = true
                edtCustome.alpha = 1F
            }
            false -> {
                edtCustome.isEnabled = false
                edtCustome.isClickable = false
                edtCustome.isFocusable = false
                edtCustome.alpha = 0.5F
            }
        }
    }

}