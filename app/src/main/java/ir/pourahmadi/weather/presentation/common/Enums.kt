package ir.pourahmadi.weather.presentation.common

enum class WearherType {
    simple, video, gallery, audio
}

enum class ImageWearherType {
    simple, video, audio
}

enum class Wearher(val type: Int) {
    Wearher_SIMPLE(1)   /*Simple Wearher Item*/,
    Wearher_VIDEO(2)   /*VIDEO Wearher Item*/,
    Wearher_GALLERY(3)   /*GALLERY Wearher Item*/,
    Wearher_AUDIO(4)   /*AUDIO Wearher Item*/,
    Wearher_SLIDER(6)   /*SLIDER Wearher Item*/,
}
