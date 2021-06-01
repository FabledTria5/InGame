package com.example.ingame.ui.activities.main

import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    fun backClicked() = router.exit()

    fun homeClicked() = router.navigateTo(screens.home())

    fun catalogueCLicked() = router.navigateTo(screens.catalogue())

    fun collectionsClicked() = router.navigateTo(screens.collections())

    fun profileClicked() = router.navigateTo(screens.profile())

}