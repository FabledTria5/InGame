package com.example.ingame.ui.fragments.catalogue

import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class CataloguePresenter @AssistedInject constructor(private val router: Router) :
    MvpPresenter<CatalogueView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onViewCreated() = viewState.setupListeners()

    fun onVoiceSearchClicked() = viewState.openDisplaySpeechRecognizer()
}