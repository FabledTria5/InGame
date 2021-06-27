package com.example.ingame.ui.fragments.info

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.ui.schedulers.Schedulers
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import moxy.MvpPresenter
import javax.inject.Inject

class InfoPresenter @AssistedInject constructor(
    @Assisted private val gameDetails: GameDetails,
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
    private val schedulers: Schedulers
) : MvpPresenter<InfoView>() {

}