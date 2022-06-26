package ir.pourahmadi.weather.presentation.ui.login.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class OtpFragmentTest {
    private val text_size_20: Float = 20.0f
    private val text_size_16: Float = 16.0f
    private val text_size_14: Float = 14.0f
    private val text_size_12: Float = 12.0f

    @get:Rule
    var hitlRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hitlRule.inject()
    }

    @Test
    fun checkViewIsDisplay() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<OtpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(allOf(withId(R.id.imgOtpLogo), isDisplayed()))

    }

    @Test
    fun checkPropertyTextviews() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<OtpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        /* check TextView Lable Property */
        onView(
            withIndex(withId(R.id.tvOtpTitle), 1)
        ).check(matches(withText(R.string.lbl_EnterAccount)))
        onView(
            withIndex(withId(R.id.tvOtpSubTitle), 1)
        ).check(matches(withText(R.string.lbl_SubEnterAccount)))
        onView(
            withIndex(withId(R.id.tvOtpLblMobile), 1)
        ).check(matches(withText(R.string.lbl_PhoneNumber)))

        /* check TextView Size Property */
        onView(
            withIndex(withId(R.id.tvOtpTitle), 1)
        ).check(matches(withFontSize(text_size_20)))
        onView(
            withIndex(withId(R.id.tvOtpSubTitle), 1)
        ).check(matches(withFontSize(text_size_14)))
        onView(
            withIndex(withId(R.id.tvOtpLblMobile), 1)
        ).check(matches(withFontSize(text_size_16)))

        /* check TextView Color Property */
        onView(withIndex(withId(R.id.tvOtpTitle), 1))
            .check(matches(hasTextColor(R.color.title)))
        onView(withIndex(withId(R.id.tvOtpSubTitle), 1))
            .check(matches(hasTextColor(R.color.subTitle)))
        onView(withIndex(withId(R.id.tvOtpLblMobile), 1))
            .check(matches(hasTextColor(R.color.title)))

    }

    @Test
    fun checkPropertyMobileEditText() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<OtpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        /* check edtMobile Property */
        onView(
            withIndex(withId(R.id.edtMobile), 1)
        ).check(matches(withHint(R.string.hint_PhoneNumber)))


        onView(
            withIndex(withId(R.id.edtMobile), 1)
        ).check(matches(withFontSize(text_size_14)))

        onView(
            withIndex(withId(R.id.edtMobile), 1)
        ).perform(setText("9354912598"))


    }

    @Test
    fun checkPropertyCountryCodeTextview() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<OtpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        /* check edtCountryCode Property */
        onView(withIndex(withId(R.id.edtCountryCode), 1))
            .check(matches(withFontSize(text_size_14)))

        onView(withIndex(withId(R.id.edtCountryCode), 1))
            .check(matches(withText("+98")))


    }

    @Test
    fun checkPropertyRollTextview() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<OtpFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        /* check edtCountryCode Property */
        onView(withIndex(withId(R.id.tvRolls), 1))
            .check(matches(withFontSize(text_size_12)))

        onView(withIndex(withId(R.id.tvRolls), 1))
            .check(matches(withText(R.string.lbl_AcceptRolls)))
        onView(withIndex(withId(R.id.tvRolls), 1))
            .check(matches(hasTextColor(R.color.title)))


    }

}