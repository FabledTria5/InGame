package com.example.ingame.ui.fragments.game

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface GamePresenterFactory {
    fun create(@Assisted("gameId") gameId: Int): GamePresenter
}