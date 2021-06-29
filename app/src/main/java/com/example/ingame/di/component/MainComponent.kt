package com.example.ingame.di.component

import android.content.Context
import com.example.ingame.MainApplication
import com.example.ingame.di.module.*
import com.example.ingame.ui.schedulers.Schedulers
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataModule::class,
        NavigationModule::class,
        NetworkModule::class,
        UiModule::class,
        UtilsModule::class,
    ]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): MainComponent

    }

}