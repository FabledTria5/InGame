package com.example.ingame.ui.fragments.home

import com.example.ingame.ui.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HomeView: BaseView {
    @AddToEndSingle
    fun updateHotGames(newPosition: Int)

    @AddToEndSingle
    fun setNewSliderItem(previousTab: Int, newTab: Int)
}