package com.example.ingame.ui.activities.main

import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val screens: IScreens,
) :
    MvpPresenter<MainView>() {

    private var lastPosition = 0

    fun onCreate() = viewState.setBottomNavTextGradient(lastPosition)

    fun backClicked() = router.exit()

    fun homeClicked() {
        router.backTo(screens.home())
        decorateBottomNavigation(newPosition = 0)
    }

    fun catalogueCLicked()  {
        router.navigateTo(screens.catalogue())
        decorateBottomNavigation(newPosition = 1)
    }

    fun collectionsClicked() {
        router.navigateTo(screens.collections())
        decorateBottomNavigation(newPosition = 2)
    }

    fun profileClicked() {
        router.navigateTo(screens.profile())
        decorateBottomNavigation(newPosition = 3)
    }

    private fun decorateBottomNavigation(newPosition: Int) {
        viewState.clearBottomNavText(lastPosition)
        viewState.setBottomNavTextGradient(newPosition)
        updateLastPosition(newPosition = newPosition)
    }

    private fun updateLastPosition(newPosition: Int) {
        lastPosition = newPosition
    }
}