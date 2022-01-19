package com.hitmeows.clock.presentation

import android.icu.util.GregorianCalendar
import android.icu.util.TimeZone
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalTime
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideCalendar() : LocalTime {
        return LocalTime.MIDNIGHT
    }
}