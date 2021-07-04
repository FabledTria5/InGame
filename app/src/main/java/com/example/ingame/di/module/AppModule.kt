package com.example.ingame.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.ingame.utils.Constants.APP_PREFERENCES
import com.example.ingame.utils.Constants.PREFERENCE_DATE
import com.example.ingame.utils.DateFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [UtilsModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    @Named(value = "lastKnownDate")
    fun provideLastKnownDate(sharedPreferences: SharedPreferences?): String =
        sharedPreferences?.getString(
            PREFERENCE_DATE, ""
        ) ?: ""

    @Singleton
    @Provides
    @Named(value = "today")
    fun provideToday(dateFormatter: DateFormatter): String = dateFormatter.getToday()

}