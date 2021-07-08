package com.example.ingame.ui.fragments.about

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.screenshots.Snapshots
import com.example.ingame.data.repository.GamesRepository
import com.example.ingame.ui.schedulers.Schedulers
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class AboutPresenter @AssistedInject constructor(
    @Assisted private val gameDetails: GameDetails?,
    private val gamesRepository: GamesRepository,
    private val schedulers: Schedulers
) : MvpPresenter<AboutView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadScreenshots()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun loadScreenshots() {
        if (gameDetails == null) {
            onGetGameInfoError()
            return
        }

        disposables += gamesRepository.getSnapshots(gameId = gameDetails.id)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onSnapshotsGetSuccess),
                onError = (::onSnapshotsGetError)
            )
    }

    private fun onGetGameInfoError() = viewState.showError()

    private fun onSnapshotsGetError(throwable: Throwable) = println(throwable.message)

    private fun onSnapshotsGetSuccess(snapshots: Snapshots) =
        viewState.setSnapshots(snapshots = snapshots.results)
}