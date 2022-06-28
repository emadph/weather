package ir.pourahmadi.weather.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import ir.pourahmadi.weather.presentation.common.gone
import ir.pourahmadi.weather.presentation.common.invisible
import ir.pourahmadi.weather.presentation.common.visible
import java.io.IOException

fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, value) -> value?.let { key to it } }.toMap()


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun getStringResources(context: Context, @StringRes resId: Int): String =
    context.resources.getString(resId)

fun getDrawable(context: Context, @DrawableRes resId: Int?): Drawable? =
    resId?.let { ContextCompat.getDrawable(context, it) }

fun getColor(context: Context, @ColorRes resId: Int): Int =
    ContextCompat.getColor(context, resId)

fun getMaterialColor(view: View, color: Int): Int =
    MaterialColors.getColor(
        view, color
    )

inline fun <T : View> T.showIf(predicate: (T) -> Boolean): T {
    if (predicate(this)) {
        visible()
    } else {
        gone()
    }
    return this
}

inline fun <T : View> T.showIfInvis(predicate: (T) -> Boolean): T {
    if (predicate(this)) {
        visible()
    } else {
        invisible()
    }
    return this
}

fun <T> RecyclerView.Adapter<*>.autoNotify(old: List<T>, new: List<T>, compare: (T, T) -> Boolean) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(old[oldItemPosition], new[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size
    })

    diff.dispatchUpdatesTo(this)
}
