package com.example.ingame.ui.fragments.hot_game

import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class HotGamePresenter(
    private val gameInfo: Result,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<HotGameView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setGameInfo(gameInfo)
    }
}