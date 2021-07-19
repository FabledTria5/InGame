package com.example.ingame.ui.fragments.catalogue

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface CatalogueView : MvpView {

    @AddToEndSingle
    fun setupListeners()

    @Skip
    fun openDisplaySpeechRecognizer()

}