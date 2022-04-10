package com.example.ingame

import com.example.ingame.di.component.DaggerMainComponent
import com.example.ingame.ui.schedulers.DefaultISchedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerMainComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultISchedulers)
            .build()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}