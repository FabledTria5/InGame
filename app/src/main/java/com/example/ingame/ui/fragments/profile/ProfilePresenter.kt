package com.example.ingame.ui.fragments.profile

import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class ProfilePresenter @AssistedInject constructor(private val router: Router) :
    MvpPresenter<ProfileView>() {

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}