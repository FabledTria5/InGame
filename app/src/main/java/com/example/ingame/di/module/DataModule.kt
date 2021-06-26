package com.example.ingame.di.module

import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRetrofitRepository(apiHelper: ApiHelper): RetrofitRepositoryImpl =
        RetrofitRepositoryImpl(apiHelper)

}