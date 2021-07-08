package com.example.ingame.ui.fragments.hot_game

import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.repository.GamesRepository
import com.example.ingame.ui.navigation.IScreens
import com.example.ingame.ui.schedulers.Schedulers
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class HotGamePresenter @AssistedInject constructor(
    @Assisted(value = "gameId") private val hotGameId: Int,
    private val schedulers: Schedulers,
    private val gamesRepository: GamesRepository,
    private val router: Router,
    private val screens: IScreens,
) : MvpPresenter<HotGameView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadHotGame()
    }

    private fun loadHotGame() {
        disposables += gamesRepository.getHotGameById(hotGameId)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetHotGameSuccess),
                onError = { onGetHotGameError() }
            )
    }

    private fun onGetHotGameError() = viewState.showError()

    private fun onGetHotGameSuccess(hotGame: HotGame) = viewState.setGameInfo(hotGame)

    fun onGameClicked() = router.navigateTo(screens.games(hotGameId))
}