package com.dmgdavid2109.dogbreeds.di

import com.dmgdavid2109.dogbreeds.BuildConfig
import com.dmgdavid2109.dogbreeds.common.network.AppSchedulerProvider
import com.dmgdavid2109.dogbreeds.common.network.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApiConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return BuildConfig.API_URL
    }

    @Provides
    @Singleton
    fun providesSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}
