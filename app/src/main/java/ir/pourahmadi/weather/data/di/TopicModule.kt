package ir.pourahmadi.weather.data.di

import ir.pourahmadi.weather.data.di.common.NetworkModule
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.api.TopicApi
import ir.pourahmadi.weather.data.repository.TopicRepositoryImpl
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.repository.TopicRepository
import ir.pourahmadi.weather.domain.use_case.TopicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class TopicModule {

    @Singleton
    @Provides
    fun provideTopicApi(retrofit: Retrofit): TopicApi {
        return retrofit.create(TopicApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTopicUseCase(
        repository: TopicRepository
    ): TopicUseCase {
        return TopicUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideTopicRepository(
        api: TopicApi,
        errorHandler: ErrorHandler,
        db: Database,
    ): TopicRepository {
        return TopicRepositoryImpl(api, errorHandler, db.commonDao)
    }


}