package com.example.ingame.ui.fragments.home

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class HomePresenter(private val homeModel: HomeModel, private val router: Router) :
    MvpPresenter<HomeView>(), TimerListener {

    init {
        homeModel.addTimerListener(this)
    }

    fun startTimer() = homeModel.startTimer()

    fun pauseTimer() = homeModel.stopTimer()

    fun tabChanged(newTab: Int) {
        viewState.updateTab(homeModel.getPreviousTab(), newTab)
        homeModel.updateTab(newTab)
    }

    override fun onTimerTick() = viewState.updateHotGames()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}