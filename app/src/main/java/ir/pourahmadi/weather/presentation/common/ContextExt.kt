package ir.pourahmadi.weather.presentation.common

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.ui.main.home.widget.AppWidget

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.updateWidget() {
    val widgetUpdateIntent = Intent(this, AppWidget::class.java).apply {
        action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        putExtra(
            AppWidgetManager.EXTRA_APPWIDGET_IDS,
            AppWidgetManager.getInstance(this@updateWidget).getAppWidgetIds(
                ComponentName(
                    this@updateWidget,
                    AppWidget::class.java
                )
            )
        )
    }
    sendBroadcast(widgetUpdateIntent)
}