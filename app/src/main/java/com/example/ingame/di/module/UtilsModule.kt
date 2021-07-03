package com.example.ingame.di.module

import com.example.ingame.utils.DateFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideDateFormatter(): DateFormatter = DateFormatter

}