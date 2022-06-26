package ir.pourahmadi.weather.data.di.common

import ir.pourahmadi.weather.utils.Validate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @Singleton
    @Provides
    fun provideValidate(): Validate {
        return Validate()
    }


}