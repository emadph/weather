package ir.pourahmadi.weather.di

import android.content.Context
import androidx.room.Room
import ir.pourahmadi.weather.BuildConfig
import ir.pourahmadi.weather.data.common.GeneralErrorHandlerImpl
import ir.pourahmadi.weather.data.di.common.CacheInterceptor
import ir.pourahmadi.weather.data.di.common.RequestInterceptor
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.api.LoginApi
import ir.pourahmadi.weather.data.remote.api.WeatherListApi
import ir.pourahmadi.weather.data.remote.api.ProfileApi
import ir.pourahmadi.weather.data.repository.WeatherRepositoryImpl
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.repository.WeatherRepository
import ir.pourahmadi.weather.utils.SharedPrefs
import ir.pourahmadi.weather.utils.ignoreAllSSLErrors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mockwebserver3.MockWebServer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    @Provides
    @Singleton
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context): Database {
        return Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    @Named("ErrorHandler")
    fun provideErrorHandlerT(): ErrorHandler {
        return GeneralErrorHandlerImpl()
    }



    @Singleton
    @Provides
    @Named("HomeApi")
    fun provideHomeApi(
        @Named("Retrofit")
        retrofit: Retrofit
    ): TopicApi {
        return retrofit.create(TopicApi::class.java)
    }

    //---------------------------------
    @Singleton
    @Provides
    @Named("WeatherRepository")
    fun provideWeatherRepository(
        @Named("WeatherListApi")
        api: WeatherListApi,
        @Named("ErrorHandler")
        errorHandler: ErrorHandler
    ): WeatherRepository {
        return WeatherRepositoryImpl(api, errorHandler)
    }

    @Singleton
    @Provides
    @Named("WeatherListApi")
    fun provideWeatherListApi(
        @Named("Retrofit")
        retrofit: Retrofit
    ): WeatherListApi {
        return retrofit.create(WeatherListApi::class.java)
    }

    //-----------------------------

    @Singleton
    @Provides
    @Named("Retrofit")
    fun provideRetrofit(
        @Named("OkHttpClient")
        okHttp: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl(BuildConfig.API_ROOT_TEST)
        }.build()
    }

    @Singleton
    @Provides
    @Named("OkHttpClient")
    fun provideOkHttp(
        @Named("RequestInterceptor")
        requestInterceptor: RequestInterceptor,
        @Named("CacheInterceptor")
        cacheInterceptor: CacheInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
//            addNetworkInterceptor(cacheInterceptor)
            ignoreAllSSLErrors()
        }.build()
    }

    @Singleton
    @Provides
    @Named("SharedPrefs")
    fun provideSharedPref(@ApplicationContext context: Context): SharedPrefs {
        return SharedPrefs(context)
    }

    @Singleton
    @Provides
    @Named("RequestInterceptor")
    fun provideRequestInterceptorT(
        @Named("SharedPrefs")
        prefs: SharedPrefs
    ): RequestInterceptor {
        return RequestInterceptor(prefs)
    }

    @Singleton
    @Provides
    @Named("CacheInterceptor")
    fun provideCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }

    //-----------------Mock----------
    @Singleton
    @Provides
    @Named("HomeRepositoryMock")
    fun provideHomeRepositoryMock(
        @Named("HomeApiMock")
        api: TopicApi,
        @Named("ErrorHandler")
        errorHandler: ErrorHandler,
        @Named("test_db")
        db: Database,
    ): TopicRepository {
        return TopicRepositoryImpl(api, errorHandler, db.commonDao)
    }

    @Singleton
    @Provides
    @Named("HomeApiMock")
    fun provideHomeApiMock(
        @Named("RetrofitMock")
        retrofit: Retrofit
    ): TopicApi {
        return retrofit.create(TopicApi::class.java)
    }

    //-------------------------------------------
    @Singleton
    @Provides
    @Named("ProfileRepositoryMock")
    fun provideProfileRepositoryMock(
        @Named("ProfileApiMock")
        profileApi: ProfileApi,
        @Named("ErrorHandler")
        errorHandler: ErrorHandler,
        @Named("test_db")
        db: Database,
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, errorHandler, db.userDao)
    }

    @Singleton
    @Provides
    @Named("ProfileApiMock")
    fun provideProfileApiMock(
        @Named("RetrofitMock")
        retrofit: Retrofit
    ): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    //--------------------------------
    @Singleton
    @Provides
    @Named("LoginRepositoryMock")
    fun provideLoginRepositoryMock(
        @Named("LoginApiMock")
        loginApi: LoginApi,
        @Named("ErrorHandler")
        errorHandler: ErrorHandler,
        @Named("test_db")
        db: Database,
        @Named("SharedPrefs")
        shareP: SharedPrefs,
    ): LoginRepository {
        return LoginRepositoryImpl(loginApi, errorHandler, db.userDao, shareP)
    }

    @Singleton
    @Provides
    @Named("LoginApiMock")
    fun provideLoginApiMock(
        @Named("RetrofitMock")
        retrofit: Retrofit
    ): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    //-----------------------------
    @Singleton
    @Provides
    @Named("RetrofitMock")
    fun provideRetrofitMock(
        @Named("OkHttpClient")
        okHttp: OkHttpClient,
        @Named("MockWebServer")
        mockWebServer: MockWebServer
    ): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl(mockWebServer.url("/"))
        }.build()
    }

    @Singleton
    @Provides
    @Named("MockWebServer")
    fun provideMockWebServer(): MockWebServer {
        return MockWebServer()
    }

}