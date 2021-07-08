package com.example.ingame.ui.fragments.hot_game

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface HotGamesPresenterFactory {
    fun create(@Assisted("gameId") hotGameId: Int): HotGamePresenter
}