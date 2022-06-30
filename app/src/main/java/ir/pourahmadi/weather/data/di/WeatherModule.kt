package ir.pourahmadi.weather.data.di

import ir.pourahmadi.weather.data.di.common.NetworkModule
import ir.pourahmadi.weather.data.local.Database
import ir.pourahmadi.weather.data.remote.api.WeatherApi
import ir.pourahmadi.weather.data.repository.WeatherRepositoryImpl
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import ir.pourahmadi.weather.domain.repository.WeatherRepository
import ir.pourahmadi.weather.domain.use_case.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.pourahmadi.weather.utils.Validate
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherUseCase(
        repository: WeatherRepository,
        validation: Validate
    ): WeatherUseCase {
        return WeatherUseCase( repository,validation)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        api: WeatherApi,
        errorHandler: ErrorHandler,
        db: Database,
    ): WeatherRepository {
        return WeatherRepositoryImpl(api, errorHandler, db.WeatherDao)
    }
}