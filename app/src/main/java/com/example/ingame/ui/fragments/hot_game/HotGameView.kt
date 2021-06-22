package com.example.ingame.ui.fragments.hot_game

import com.example.ingame.data.network.model.games_list.Result
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HotGameView : MvpView {

    @AddToEndSingle
    fun setGameInfo(gameInfo: Result)

}