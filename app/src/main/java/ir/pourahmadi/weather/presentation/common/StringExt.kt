package ir.pourahmadi.weather.presentation.common

import android.text.TextUtils
import android.util.Patterns
import androidx.core.util.PatternsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun String.isEmail(): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPhoneNo(): Boolean {
    return Patterns.PHONE.matcher(this).matches()
}

fun String.imagesToList(): List<String> {
    var result: List<String>
    this.let {
        val type = object : TypeToken<List<String>>() {}.type
        result = Gson().fromJson(
            this,
            type
        )
    }
    return result
}

fun isOtpCode(otpCode: String): Boolean {
    return otpCode.isNumber()
}

fun String.isNumber(): Boolean {
    return TextUtils.isDigitsOnly(this)
}

fun isPublicName(publicName: String): Boolean {
    val regex = Regex("^[A-Za-z0-9_.]*$")
    return regex.containsMatchIn(input = publicName)
}

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    return this?.let {
        val regex = if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr)
        regex.findAll(this).map { it.range.start }.toList()
    } ?: emptyList()
}
