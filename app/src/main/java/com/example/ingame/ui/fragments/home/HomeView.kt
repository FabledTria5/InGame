package com.example.ingame.ui.fragments.home

import com.example.ingame.ui.base.TabView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface HomeView : TabView {
    @AddToEndSingle
    fun updateHotGames(newPosition: Int)

    @AddToEndSingle
    fun setNewSliderItem(previousTab: Int, newTab: Int)

    @AddToEndSingle
    fun setupGamesViewPager()

    @AddToEndSingle
    fun setupSlider(hotGamesIds: List<Int>)

    @Skip
    fun showError()

    @Skip
    fun updateDate(newDate: String)
}