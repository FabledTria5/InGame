package com.example.ingame.ui.fragments.game

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.ui.base.TabView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface GameView : TabView {

    @AddToEndSingle
    fun setGameData(gameDetails: GameDetails)

    @AddToEndSingle
    fun initViewPager(gameDetails: GameDetails)
}