package com.example.ingame.ui.fragments.home

import com.example.ingame.ui.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface HomeView: BaseView {
    fun updateHotGames()
    fun updateTab(previousTab: Int, newTab: Int)
}