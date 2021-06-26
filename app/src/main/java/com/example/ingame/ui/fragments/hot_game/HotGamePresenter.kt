package com.example.ingame.ui.fragments.hot_game

import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class HotGamePresenter @AssistedInject constructor (
    @Assisted(value = "gameInfo") private val gameInfo: Result,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<HotGameView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setGameInfo(gameInfo)
    }

    fun onGameClicked() = router.navigateTo(screens.games(gameInfo.id))
}