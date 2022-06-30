package ir.pourahmadi.weather.presentation.ui.main.home.widget

import android.app.PendingIntent

import android.content.Intent

import android.widget.RemoteViews

import android.appwidget.AppWidgetManager

import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import ir.pourahmadi.weather.BuildConfig
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class AppWidget : AppWidgetProvider() {
    @set:Inject
    lateinit var db: Database

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        coroutineScope.launch {
            val weather = db.WeatherDao.getWeather()
            appWidgetIds.forEach { appWidgetId ->
                val pendingIntent: PendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    Intent(context, MainActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                val views: RemoteViews = RemoteViews(
                    context.packageName,
                    R.layout.layout_widget
                ).apply {
                    if (weather != null) {
                        setTextViewText(R.id.tvCityName, weather.cityName)
                        setTextViewText(R.id.tvTemp, weather.temperature)

                        val weatherIconUrl =
                            BuildConfig.WEATHER_ICON_BASE_URL + weather.weatherIcon + "@4x.png"

                        val awt: AppWidgetTarget = object : AppWidgetTarget(
                            context.applicationContext,
                            R.id.imgWeatherIcon, this, *appWidgetIds
                        ) {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                super.onResourceReady(resource, transition)
                            }
                        }
                        Glide.with(context.applicationContext).asBitmap().load(weatherIconUrl)
                            .into(awt)

                    }
                    setOnClickPendingIntent(R.id.mainLayout, pendingIntent)
                }
                updateAppWidget(views, appWidgetManager, appWidgetId)

            }

        }


    }


    override fun onDisabled(context: Context) {
        job.cancel()
    }

    private fun updateAppWidget(
        views: RemoteViews,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        appWidgetManager.partiallyUpdateAppWidget(appWidgetId, views)
    }

}