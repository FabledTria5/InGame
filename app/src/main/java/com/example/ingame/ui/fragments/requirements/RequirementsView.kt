package com.example.ingame.ui.fragments.requirements

import com.example.ingame.ui.base.ErrorView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface RequirementsView : ErrorView {

    @AddToEndSingle
    fun showRequirements(requirements: Pair<String, String>)

    @AddToEndSingle
    fun showEmptyRequirements()

}