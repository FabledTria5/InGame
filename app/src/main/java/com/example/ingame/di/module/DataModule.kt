package com.example.ingame.di.module

import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofitRepository(apiHelper: ApiHelper): RetrofitRepositoryImpl =
        RetrofitRepositoryImpl(apiHelper)

    @Provides
    fun provideUiThreads(): Scheduler = AndroidSchedulers.mainThread()

}