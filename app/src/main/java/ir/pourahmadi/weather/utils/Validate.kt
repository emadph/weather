package ir.pourahmadi.weather.utils

import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.common.isNumber
import ir.pourahmadi.weather.presentation.common.isPublicName

class Validate {

    fun validateProfilepublicName(
        publicName: String
    ): Pair<Boolean, Int?> {
        var resultBoolean = true
        var resultResId: Int? = null

        if (publicName.isEmpty()) {
            resultResId = R.string.msg_error_input_empty_publicname
            resultBoolean = false
        } else if (publicName.length !in 5..30) {
            resultResId = R.string.msg_error_input_wrongCountUser
            resultBoolean = false
        } else if (publicName.startsWith(".") || publicName.endsWith(".")) {
            resultResId = R.string.msg_error_input_wrongDotUser
            resultBoolean = false
        } else if (publicName.contains("..") || publicName.contains("__")) {
            resultResId = R.string.msg_error_input_wrongUnderLineUser
            resultBoolean = false
        } else if (publicName.isNumber()) {
            resultResId = R.string.msg_error_input_wrongNumberUser
            resultBoolean = false
        } else if (!isPublicName(publicName)) {
            resultResId = R.string.msg_error_input_publicName_notMatch
            resultBoolean = false
        }
        return Pair(resultBoolean, resultResId)
    }


}