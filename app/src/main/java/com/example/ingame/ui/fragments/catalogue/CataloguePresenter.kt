package com.example.ingame.ui.fragments.catalogue

import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class CataloguePresenter @AssistedInject constructor(
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<CatalogueView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onCreateView() = viewState.setupMenu()

    fun onSearchClicked(): Boolean {
        router.navigateTo(screen = screens.search())
        return true
    }
}