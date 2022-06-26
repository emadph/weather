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
class LoginFragmentTest {
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

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(allOf(withId(R.id.imgLoginLogo), isDisplayed()))
        onView(allOf(withId(R.id.tvLoginTitle), isDisplayed()))

    }

    @Test
    fun checkPropertyTextviews() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        /* check TextView Lable Property */
        onView(
            allOf(
                withId(R.id.tvLoginTitle),
                isDisplayed()
            )
        ).check(matches(withText(R.string.lbl_EnterAccount)))
        onView(
            allOf(
                withId(R.id.tvLoginTitle),
                isDisplayed()
            )
        ).check(matches(hasTextColor(R.color.title)))
        onView(
            allOf(
                withId(R.id.tvLoginLblMobile),
                isDisplayed()
            )
        ).check(matches(withText(R.string.lbl_OtpCode)))


        /* check TextView Size Property */
        onView(allOf(withId(R.id.tvLoginTitle), isDisplayed())).check(
            matches(
                withFontSize(
                    text_size_20
                )
            )
        )
        onView(allOf(withId(R.id.tvLoginSubTitle), isDisplayed())).check(
            matches(
                withFontSize(
                    text_size_14
                )
            )
        )
        onView(allOf(withId(R.id.tvLoginLblMobile), isDisplayed())).check(
            matches(
                withFontSize(
                    text_size_16
                )
            )
        )
        onView(
            allOf(
                withId(R.id.tvTimer),
                isDisplayed()
            )
        ).check(matches(withFontSize(text_size_12)))

        /* check TextView Color Property */
        onView(
            allOf(
                withId(R.id.tvLoginSubTitle),
                isDisplayed()
            )
        ).check(matches(hasTextColor(R.color.subTitle)))
        onView(
            allOf(
                withId(R.id.tvLoginLblMobile),
                isDisplayed()
            )
        ).check(matches(hasTextColor(R.color.title)))

    }

}