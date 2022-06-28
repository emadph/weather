package ir.pourahmadi.weather.data.local

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
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
class UserDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: Database
    private lateinit var userDao: UserDao


    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.userDao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUser() = runBlocking {
        var user: UserEntity? = null
        val count = 5
        for (i in 1..count) {
            user = UserEntity(
                id = i,
                publicName = "publicName",
                fullName = "fullName",
                bio = "bio",
                sex = "sex",
                birthdatePersian = "birthdatePersian",
                provinceId = i,
                latitudes = "latitudes",
                longitudes = "longitudes",
                avatar = "avatar",
                userAge = "userAge"
            )
            userDao.insertUser(user)
        }
        val userList = userDao.getUser()

        assertThat(userList).contains(user)
    }

    @Test
    fun getUser() = runBlocking {
        var user: UserEntity? = null
        for (i in 1..1) {
            user = UserEntity(
                id = i,
                publicName = "publicName",
                fullName = "fullName",
                bio = "bio",
                sex = "sex",
                birthdatePersian = "birthdatePersian",
                provinceId = i,
                latitudes = "latitudes",
                longitudes = "longitudes",
                avatar = "avatar",
                userAge = "userAge"
            )
            userDao.insertUser(user)
        }
        val userList = userDao.getUser()

        assertThat(userList).contains(user)
    }

    @Test
    fun deleteUserById() = runBlocking {
        var user: UserEntity? = null
        for (i in 1..10) {
            user = UserEntity(
                id = i,
                publicName = "publicName",
                fullName = "fullName",
                bio = "bio",
                sex = "sex",
                birthdatePersian = "birthdatePersian",
                provinceId = i,
                latitudes = "latitudes",
                longitudes = "longitudes",
                avatar = "avatar",
                userAge = "userAge"
            )
            userDao.insertUser(user)
        }
        userDao.deleteUser(user!!)

        val userList = userDao.getUser()

        assertThat(userList).doesNotContain(user)
    }
}