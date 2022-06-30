package ir.pourahmadi.weather.utils

import ir.pourahmadi.weather.R

class Validate {

    fun validateCityName(
        cityName: String
    ): Pair<Boolean, Int?> {
        var resultBoolean = true
        var resultResId: Int? = null

        if (cityName.isEmpty()) {
            resultResId = R.string.msg_error_input_empty_cityName
            resultBoolean = false
        }
        return Pair(resultBoolean, resultResId)
    }

    fun validateCityLocation(
        lat: String, lng :String
    ): Pair<Boolean, Int?> {
        var resultBoolean = true
        var resultResId: Int? = null

        if (lat.isEmpty() || lng.isEmpty() ) {
            resultResId = R.string.msg_error_input_empty_latLng
            resultBoolean = false
        }
        return Pair(resultBoolean, resultResId)
    }


}