package ir.pourahmadi.weather.utils

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ValidateTest {

    lateinit var validate: Validate

    @Before
    fun setUp() {
        validate = Validate()
    }

    //login tests
    @Test
    fun phoneIsEmpty_false() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "",
            cCode = "+98"
        )
        assertThat(resultBoolean).isFalse()
    }

    @Test
    fun phoneIsNotDigit_false() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "sdfdsasdf",
            cCode = "+98"
        )
        assertThat(resultBoolean).isFalse()
    }

    @Test
    fun phoneIsOk_true() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "09353588937",
            cCode = "+98"
        )
        assertThat(resultBoolean).isTrue()
    }

    @Test
    fun CountryCodeIsEmpty_false() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "09353588937",
            cCode = ""
        )
        assertThat(resultBoolean).isFalse()
    }

    //---------------
    @Test
    fun OtpCodeIsEmpty_false() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "09353588937",
            cCode = "+98",
            otpCode = ""
        )
        assertThat(resultBoolean).isFalse()
    }

    @Test
    fun OtpCodeNotDigit_false() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "09353588937",
            cCode = "+98",
            otpCode = "dfdfdf"
        )
        assertThat(resultBoolean).isFalse()
    }

    @Test
    fun OtpCodeIsOk_true() {
        val (resultBoolean, resultResId) = validate.validateOtpInputs(
            phone = "09353588937",
            cCode = "+98",
            otpCode = "1234"
        )
        assertThat(resultBoolean).isTrue()
    }

    //profile tests
    @Test
    fun publicName_isEmpty_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = ""
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_empty_publicname)
    }

    @Test
    fun publicName_LengthLessThan5_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "adg"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongCountUser)
    }

    @Test
    fun publicName_LengthMoreThan30_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "asdfghjkqwertyuazxcfvbnjgredkhhf"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongCountUser)
    }

    @Test
    fun publicName_startAndEndWithDot_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = ".asdfghj."
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongDotUser)
    }

    @Test
    fun publicName_startWithDot_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = ".asdfghj"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongDotUser)
    }

    @Test
    fun publicName_endWithDot_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "asdfghj."
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongDotUser)
    }

    @Test
    fun publicName_containTowUndeline_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "asdf__ghj"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongUnderLineUser)
    }

    @Test
    fun publicName_containTowDot_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "asdf..ghj"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongUnderLineUser)
    }

    @Test
    fun publicName_onlyDigit_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "123456789"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_wrongNumberUser)
    }

    @Test
    fun publicName_notMatchToRegex_false() {
        val (resultBoolean, resultResId) = validate.validateProfilepublicName(
            publicName = "hadi_dehgh@ni.t"
        )
        assertThat(resultBoolean).isFalse()
        assertThat(resultResId).isEqualTo(R.string.msg_error_input_publicName_notMatch)
    }
}