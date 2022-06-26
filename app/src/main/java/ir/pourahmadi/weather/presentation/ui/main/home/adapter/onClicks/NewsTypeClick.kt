package ir.pourahmadi.weather.presentation.ui.main.home.adapter.onClicks

sealed class WearherTypeClick<out T> {
    data class onItemClick<out T>(val value: T? = null) : WearherTypeClick<T>()
    data class onItemClickMore<out T>(val value: T? = null) : WearherTypeClick<T>()
    data class onItemClickLike<out T>(val value: T? = null) : WearherTypeClick<T>()
    data class onItemClickBookmark<out T>(val value: T? = null) : WearherTypeClick<T>()
    data class onItemClickFullSize<out T>(val value: T? = null) : WearherTypeClick<T>()
}
