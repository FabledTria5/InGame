package com.example.ingame.ui.fragments.profile

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ProfilePresenter(private val router: Router) : MvpPresenter<ProfileView>() {

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}