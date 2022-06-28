package ir.pourahmadi.weather.data.repository.Weather

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.domain.common.base.BaseResult
import ir.pourahmadi.weather.domain.repository.WeatherRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URI
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
class WeatherRepositoryRealApiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("WeatherRepository")
    lateinit var repo: WeatherRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getWeatherList_onTestApi_true() = runBlocking {
        var url = "https://borsa.ir/Weatherapi/api/v1/Posts/topics/2?page=1"
        val index = 1
        repo.getWeatherList(increaseIndex(url, index))
            .collect { baseResult ->
                assertThat(baseResult is BaseResult.Success).isTrue()
            }
    }

    private fun increaseIndex(url: String, pageIndex: Int): String {
        if (pageIndex == 1) return url

        val baseUrl = getBaseUrl(url)
        val newPageParam = "page=$pageIndex"
        return appendUri(baseUrl, newPageParam)
    }

    private fun appendUri(uri: String, appendQuery: String): String {
        val oldUri = URI(uri)
        var newQuery = oldUri.query
        if (newQuery == null) {
            newQuery = appendQuery
        } else {
            newQuery += "&$appendQuery"
        }
        return URI(
            oldUri.scheme, oldUri.authority,
            oldUri.path, newQuery, oldUri.fragment
        ).toString()
    }

    private fun getBaseUrl(url: String): String {
        val uri = URI(url)
        return URI(
            uri.scheme,
            uri.authority,
            uri.path,
            null,  // Ignore the query part of the input url
            uri.fragment
        ).toString()
    }

}