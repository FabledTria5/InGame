package com.example.ingame.ui.fragments.game

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.ui.schedulers.Schedulers
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class GamePresenter(
    private val gameId: Int,
    private val router: Router,
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
    private val schedulers: Schedulers
) :
    MvpPresenter<GameView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadGameData()
    }

    private fun loadGameData() {
        disposables += retrofitRepositoryImpl.getGameDetails(gameId)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetGameSuccess),
                onError = (::onGetGameError)
            )
    }

    private fun onGetGameSuccess(gameDetails: GameDetails) =
        viewState.setGameData(gameDetails = gameDetails)

    private fun onGetGameError(throwable: Throwable) {
        println("Error")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
