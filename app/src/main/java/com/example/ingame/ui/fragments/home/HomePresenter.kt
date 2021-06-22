package com.example.ingame.ui.fragments.home

import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.navigation.IScreens
import com.example.ingame.utils.Constants.HOT_GAMES_TICK_RATE
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import java.io.Serializable
import java.util.concurrent.TimeUnit

class HomePresenter(
    private val uiScheduler: Scheduler,
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
    private val router: Router,
    private val screens: IScreens,
) :
    MvpPresenter<HomeView>() {

    private val disposables = CompositeDisposable()

    var wasDragging: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getSliderGames()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun getSliderGames() {
        disposables += retrofitRepositoryImpl.getListOfGames(
            1,
            "2021-05-01,2021-06-01",
            pageSize = 5
        )
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = (::onGetSliderGamesSuccess),
                onError = (::onGetSliderGamesError)
            )
    }

    private fun onGetSliderGamesSuccess(gamesList: GamesList) {
        val arrayOfSliderFragments = arrayListOf<HotGameFragment>()
        gamesList.results.forEach { result ->
            arrayOfSliderFragments.add(
                HotGameFragment.newInstance(
                    result,
                    onGameClickListener = object : OnGameClickListener, Serializable {
                        override fun onGameClicked(gameId: Int) {
                            router.navigateTo(screens.games(gameId = gameId))
                        }
                    }
                ) as HotGameFragment
            )
        }
        viewState.setupSlider(arrayOfSliderFragments)
    }

    private fun onGetSliderGamesError(error: Throwable) {
        println(error.message)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}