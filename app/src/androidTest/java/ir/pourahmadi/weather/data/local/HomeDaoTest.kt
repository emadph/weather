package ir.pourahmadi.weather.data.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import ir.pourahmadi.weather.data.local.entity.TopicEntity
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
@SmallTest
@HiltAndroidTest
class HomeDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: Database
    private lateinit var homeDao: CommonDao


    @Before
    fun setup() {
        hiltRule.inject()
        homeDao = database.commonDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertTopics() = runBlocking {
        var entity: TopicEntity? = null
        val count = 40
        for (i in 1..count) {
            entity = TopicEntity(
                id = i,
                link = "link",
                title = "title $i",
                parentId = null,
                topicTypesId = i,
                x = 2,
                y = 2,
                isRemovable = false,
                isMovable = false
            )
            homeDao.insertTopic(entity)
        }
        val mList = homeDao.getTopics()

        assertThat(mList).contains(entity)
    }

    @Test
    fun getTopicsAndSub() = runBlocking {
        var entity: TopicEntity? = null
        var entitySub: TopicEntity? = null
        val count = 5
        for (i in 1..count) {
            entity = TopicEntity(
                id = i,
                link = "link",
                title = "title $i",
                parentId = null,
                topicTypesId = i,
                x = 2,
                y = 2,
                isRemovable = false,
                isMovable = false
            )
            homeDao.insertTopic(entity)
            if (i > 2) {
                for (j in (i * 20)..(i * 25)) {
                    entitySub = TopicEntity(
                        id = j,
                        link = "link",
                        title = "title $j",
                        parentId = i,
                        topicTypesId = j,
                        x = 2,
                        y = 2,
                        isRemovable = false,
                        isMovable = false
                    )
                    homeDao.insertTopic(entitySub)
                }
            }
        }
        val mList = homeDao.getTopicsAndSub()
            .filter {
                it.subTopicList.isNotEmpty() ||
                        it.topic.parentId == null
            }

        assertThat(mList[4].subTopicList).contains(entitySub)
        assertThat(mList[4].topic).isEqualTo(entity)
    }


}