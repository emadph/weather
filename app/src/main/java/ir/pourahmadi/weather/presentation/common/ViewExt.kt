package ir.pourahmadi.weather.presentation.common

import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.forEach
import com.bumptech.glide.Glide
import ir.pourahmadi.weather.R

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibility(loading: Boolean) {
    when (loading) {
        true -> {
            visible()
        }
        false -> {
            gone()
        }
    }
}

fun ViewGroup.disableEnableControls(function: View.() -> Unit) {
    this.forEach { child ->
        child.function()
        if (child is ViewGroup) {
            child.disableEnableControls(function)
        }
    }
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)

}

fun EditText.onDone(callback: () -> Unit) {
    // These lines optional if you don't want to set in Xml
    imeOptions = EditorInfo.IME_ACTION_DONE
    maxLines = 1
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            true
        }
        false
    }
}
