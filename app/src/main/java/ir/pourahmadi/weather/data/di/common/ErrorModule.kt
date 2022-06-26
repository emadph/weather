package ir.pourahmadi.weather.data.di.common

import ir.pourahmadi.weather.data.common.GeneralErrorHandlerImpl
import ir.pourahmadi.weather.domain.common.error.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ErrorModule {

    @Singleton
    @Provides
    fun provideErrorHandler(): ErrorHandler {
        return GeneralErrorHandlerImpl()
    }


}