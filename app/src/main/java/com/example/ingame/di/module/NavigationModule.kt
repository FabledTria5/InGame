package com.example.ingame.di.module

import com.example.ingame.ui.navigation.ApplicationScreens
import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Singleton
    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun provideScreens(): IScreens = ApplicationScreens()

}