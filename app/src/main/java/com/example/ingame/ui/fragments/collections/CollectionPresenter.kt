package com.example.ingame.ui.fragments.collections

import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class CollectionPresenter @AssistedInject constructor(private val router: Router) :
    MvpPresenter<CollectionsView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}