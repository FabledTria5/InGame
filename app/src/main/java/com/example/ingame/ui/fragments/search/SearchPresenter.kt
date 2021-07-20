package com.example.ingame.ui.fragments.search

import com.example.ingame.data.repository.IGamesRepository
import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class SearchPresenter @AssistedInject constructor(
    private val router: Router,
    private val screens: IScreens,
    private val gamesRepository: IGamesRepository
) : MvpPresenter<SearchView>() {

    fun onCreateView() = viewState.setupMenu()

    fun onViewCreated() = viewState.setupListeners()

    fun onVoiceSearchClicked() = viewState.openDisplaySpeechRecognizer()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}