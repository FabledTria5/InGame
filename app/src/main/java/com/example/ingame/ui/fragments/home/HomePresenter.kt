package com.example.ingame.ui.fragments.home

import com.example.ingame.utils.Constants.HOT_GAMES_TICK_RATE
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class HomePresenter(
    private val uiScheduler: Scheduler,
    private val homeModel: HomeModel,
    private val router: Router
) :
    MvpPresenter<HomeView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        startTimer()
    }

    private fun startTimer() {
        disposables += Observable.interval(HOT_GAMES_TICK_RATE, TimeUnit.SECONDS, uiScheduler)
            .subscribe {
                if (homeModel.isSliderFinished()) {
                    viewState.updateHotGames(newPosition = 0)
                    homeModel.resetSliderItem()
                } else viewState.updateHotGames(homeModel.nextPage())
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun pageChanged(position: Int) = homeModel.setCurrentSliderItem(position)

}