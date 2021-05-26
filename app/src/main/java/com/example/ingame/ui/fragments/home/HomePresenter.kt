package com.example.ingame.ui.fragments.home

import moxy.MvpPresenter

class HomePresenter(private val homeModel: HomeModel) : MvpPresenter<HomeView>(), TimerListener {

    init {
        homeModel.addTimerListener(this)
    }

    fun startTimer() = homeModel.startTimer()

    fun pauseTimer() = homeModel.stopTimer()

    override fun onTimerTick() = viewState.updateHotGames()
}