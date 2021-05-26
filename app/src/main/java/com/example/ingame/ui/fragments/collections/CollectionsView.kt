package com.example.ingame.ui.fragments.collections

import com.example.ingame.ui.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CollectionsView : BaseView