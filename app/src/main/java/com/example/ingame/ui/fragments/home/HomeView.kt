package com.example.ingame.ui.fragments.home

import com.example.ingame.ui.base.TabView
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface HomeView : TabView {
    @AddToEndSingle
    fun updateHotGames(newPosition: Int)

    @AddToEndSingle
    fun setNewSliderItem(previousTab: Int, newTab: Int)

    @AddToEndSingle
    fun setupGamesViewPager()

    @Skip
    fun showError()

    @AddToEndSingle
    fun setupSlider(arrayList: ArrayList<HotGameFragment>)
}