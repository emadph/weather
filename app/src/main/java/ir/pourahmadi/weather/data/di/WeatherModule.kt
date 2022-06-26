package ir.pourahmadi.weather.data.di

import android.content.Context
import ir.pourahmadi.weather.data.di.common.NetworkModule
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.api.WearherListApi
import ir.pourahmadi.weather.data.repository.WearherRepositoryImpl
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.repository.WearherRepository
import ir.pourahmadi.weather.domain.use_case.WearherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class WearherModule {

    @Singleton
    @Provides
    fun provideWearherApi(retrofit: Retrofit): WearherListApi {
        return retrofit.create(WearherListApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWearherUseCase(
        repository: WearherRepository,
        @ApplicationContext context: Context
    ): WearherUseCase {
        return WearherUseCase(context, repository)
    }

    @Singleton
    @Provides
    fun provideWearherRepository(
        api: WearherListApi,
        errorHandler: ErrorHandler,
        db: Database,
    ): WearherRepository {
        return WearherRepositoryImpl(api, errorHandler, db.WearherDao)
    }
}