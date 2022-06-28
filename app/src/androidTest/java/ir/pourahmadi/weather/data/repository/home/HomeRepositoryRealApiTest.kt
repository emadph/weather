package ir.pourahmadi.weather.data.repository.home

import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.domain.common.base.BaseResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeRepositoryRealApiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("HomeRepository")
    lateinit var repo: TopicRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getHomeTabs_onTestApi_true() = runBlocking {
        repo.getTopics()
            .collect { baseResult ->
                assertThat(baseResult is BaseResult.Success).isTrue()
            }
    }
}