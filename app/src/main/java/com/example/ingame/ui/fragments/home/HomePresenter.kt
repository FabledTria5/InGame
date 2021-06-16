package com.example.ingame.ui.fragments.home

import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.utils.Constants.HOT_GAMES_TICK_RATE
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class HomePresenter(
    private val uiScheduler: Scheduler,
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
    private val homeModel: HomeModel,
    private val router: Router
) :
    MvpPresenter<HomeView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getSliderGames()
    }

    private fun getSliderGames() {
        disposables += retrofitRepositoryImpl.getListOfGames(1, "2021-05-01,2021-06-01")
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = (::onGetSliderGamesSuccess),
                onError = (::onGetSliderGamesError)
            )
    }

    private fun onGetSliderGamesSuccess(gamesList: GamesList) {
        println("Got it!")
    }

    private fun onGetSliderGamesError(error: Throwable) {
        println(error.message)
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