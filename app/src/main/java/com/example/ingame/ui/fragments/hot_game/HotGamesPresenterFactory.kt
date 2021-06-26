package com.example.ingame.ui.fragments.hot_game

import com.example.ingame.data.network.model.games_list.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface HotGamesPresenterFactory {
    fun create(@Assisted("gameInfo") gameInfo: Result): HotGamePresenter
}