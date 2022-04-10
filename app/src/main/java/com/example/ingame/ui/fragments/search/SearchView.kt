package com.example.ingame.ui.fragments.search

import com.example.ingame.ui.base.ErrorView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface SearchView : ErrorView {

    @AddToEndSingle
    fun setupListeners()

    @Skip
    fun openDisplaySpeechRecognizer()

    @AddToEndSingle
    fun setupMenu()

    @AddToEndSingle
    fun setLoading(isLoading: Boolean)

}