package ir.pourahmadi.weather.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun <K, V> Map<K, V?>.filterNotNullValues(): Map<K, V> =
    mapNotNull { (key, value) -> value?.let { key to it } }.toMap()


fun getStringResources(context: Context, @StringRes resId: Int): String =
    context.resources.getString(resId)

fun getDrawable(context: Context, @DrawableRes resId: Int?): Drawable? =
    resId?.let { ContextCompat.getDrawable(context, it) }

fun getColor(context: Context, @ColorRes resId: Int): Int =
    ContextCompat.getColor(context, resId)
