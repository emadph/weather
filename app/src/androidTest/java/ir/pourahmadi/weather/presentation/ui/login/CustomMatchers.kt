package ir.pourahmadi.weather.presentation.ui.login.fragments


import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


fun setText(text: String?): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(isDisplayed(), isAssignableFrom(TextView::class.java))
        }

        override fun getDescription(): String {
            return "Change view text"
        }

        override fun perform(uiController: UiController?, view: View) {
            (view as TextView).text = text
        }
    }
}

internal class withFontSize(private val mExpectedFontSize: Float) :
    TypeSafeMatcher<View?>(View::class.java) {
    private var actualFontSize = 0
    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextView) {
            return false
        }
        val pixel = item.textSize.toInt()
        actualFontSize = (pixel / item.getResources().displayMetrics.scaledDensity).toInt()
        return mExpectedFontSize.toInt() == actualFontSize
    }

    override fun describeTo(description: Description) {
        description.appendText("Text Size did not match $mExpectedFontSize was $actualFontSize")
    }
}

fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?> {
    return object : TypeSafeMatcher<View?>() {
        var currentIndex = 0
        override fun describeTo(description: Description) {
            description.appendText("with index: ")
            description.appendValue(index)
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: View?): Boolean {
            return matcher.matches(view) && currentIndex++ == index
        }
    }
}

fun getTextColor(matcher: Matcher<View>): String {
    val stringHolder = arrayOf<String>(null.toString())
    onView(matcher).perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(TextView::class.java)
        }

        override fun getDescription(): String {
            return "getting text color from a TextView"
        }

        override fun perform(uiController: UiController, view: View) {
            val tv = view as TextView
            stringHolder[0] = tv.currentTextColor.toString()
        }
    })
    return stringHolder[0]
}