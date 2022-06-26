package ir.pourahmadi.weather.presentation.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.launchFragmentInHiltContainer
import ir.pourahmadi.weather.presentation.ui.login.fragments.withFontSize
import ir.pourahmadi.weather.presentation.ui.login.fragments.withIndex
import ir.pourahmadi.weather.presentation.ui.main.uiCard.fragments.UiCardsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class UiCardsFragmentTest {
    private val text_size_20: Float = 20.0f
    private val text_size_16: Float = 16.0f
    private val text_size_14: Float = 14.0f
    private val text_size_12: Float = 12.0f
    private val text_size_10: Float = 10.0f

    @get:Rule
    var hitlRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hitlRule.inject()
    }

    @Test
    fun checkViewIsDisplay() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<UiCardsFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(
            Matchers.allOf(
                withId(R.id.tvWearherSimpleCategoryTitle),
                isDisplayed()
            )
        )

    }

    @Test
    fun checkPropertyItemWearherCardSimple() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<UiCardsFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        /* check TextView Size Property */
        onView(
            withIndex(withId(R.id.tvWearherSimpleCategoryTitle), 1)
        ).check(matches(withFontSize(text_size_12)))
        onView(
            withIndex(withId(R.id.tvWearherSimpleDatePublish), 1)
        ).check(matches(withFontSize(text_size_10)))
        onView(
            withIndex(withId(R.id.tvWearherSimpleTitle), 1)
        ).check(matches(withFontSize(text_size_16)))
        onView(
            withIndex(withId(R.id.tvWearherSimpleBriefDesc), 1)
        ).check(matches(withFontSize(text_size_12)))


        /* check TextView Color Property */
        onView(withIndex(withId(R.id.tvWearherSimpleCategoryTitle), 1))
            .check(matches(hasTextColor(R.color.title)))
        onView(withIndex(withId(R.id.tvWearherSimpleDatePublish), 1))
            .check(matches(hasTextColor(R.color.subTitle)))
        onView(withIndex(withId(R.id.tvWearherSimpleTitle), 1))
            .check(matches(hasTextColor(R.color.title)))
        onView(withIndex(withId(R.id.tvWearherSimpleBriefDesc), 1))
            .check(matches(hasTextColor(R.color.subTitle)))


    }

}