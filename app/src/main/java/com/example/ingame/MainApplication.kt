package com.example.ingame

import com.example.ingame.di.component.DaggerMainComponent
import com.example.ingame.ui.schedulers.DefaultSchedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerMainComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultSchedulers)
            .build()
}