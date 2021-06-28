package com.example.ingame.ui.fragments.info

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.ui.schedulers.Schedulers
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class InfoPresenter @AssistedInject constructor(
    @Assisted private val gameDetails: GameDetails,
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
    private val schedulers: Schedulers
) : MvpPresenter<InfoView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getDevelopmentTeam()
    }

    private fun getDevelopmentTeam() {
        disposables += retrofitRepositoryImpl.getDevelopers(gameId = gameDetails.id)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetDevelopersSuccess)
            )
    }

    private fun onGetDevelopersSuccess(gameDevelopers: GameDevelopers) =
        viewState.setInfo(gameDevelopers)
}