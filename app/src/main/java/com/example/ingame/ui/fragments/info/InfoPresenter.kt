package com.example.ingame.ui.fragments.info

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.repository.GamesRepository
import com.example.ingame.ui.schedulers.Schedulers
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class InfoPresenter @AssistedInject constructor(
    @Assisted private val gameDetails: GameDetails?,
    private val gamesRepository: GamesRepository,
    private val schedulers: Schedulers
) : MvpPresenter<InfoView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getDevelopmentTeam()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun getDevelopmentTeam() {
        if (gameDetails == null) {
            onGetError()
            return
        }

        disposables += gamesRepository.getDevelopers(gameId = gameDetails.id)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetDevelopersSuccess),
                onError = (::onGetError)
            )
    }

    private fun onGetError() = viewState.showError()

    private fun onGetError(throwable: Throwable) = println(throwable.message)

    private fun onGetDevelopersSuccess(gameDevelopers: GameDevelopers) =
        viewState.setInfo(gameDevelopers)
}