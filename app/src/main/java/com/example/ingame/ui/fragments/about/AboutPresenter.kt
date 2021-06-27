package com.example.ingame.ui.fragments.about

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.screenshots.Screenshots
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.ui.schedulers.Schedulers
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class AboutPresenter @AssistedInject constructor(
    @Assisted private val gameDetails: GameDetails,
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
    private val schedulers: Schedulers
) : MvpPresenter<AboutView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadScreenshots()
    }

    private fun loadScreenshots() {
        retrofitRepositoryImpl.getSnapshots(gameId = gameDetails.id)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onSnapshotsGetSuccess),
                onError = (::onSnapshotsGetError)
            )
    }

    private fun onSnapshotsGetError(throwable: Throwable) {
        println(throwable.message)
    }

    private fun onSnapshotsGetSuccess(screenshots: Screenshots) =
        viewState.setSnapshots(snapshots = screenshots.results)
}