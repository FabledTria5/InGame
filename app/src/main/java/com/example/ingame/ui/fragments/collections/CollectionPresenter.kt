package com.example.ingame.ui.fragments.collections

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CollectionPresenter(private val router: Router) : MvpPresenter<CollectionsView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}