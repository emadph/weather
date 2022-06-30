package ir.pourahmadi.weather.presentation.common

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.utils.getColor


inline fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    ViewCompat.setLayoutDirection(snack.view, ViewCompat.LAYOUT_DIRECTION_RTL);
    val text: TextView = snack.view.findViewById(R.id.snackbar_action)
    TextViewCompat.setTextAppearance(text, R.style.TextTitlePrimaryBold);
    text.setTextColor(getColor(snack.context, R.color.white))
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
