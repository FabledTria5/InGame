package com.example.ingame

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class MvpApplication : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: MvpApplication
            private set
    }

}