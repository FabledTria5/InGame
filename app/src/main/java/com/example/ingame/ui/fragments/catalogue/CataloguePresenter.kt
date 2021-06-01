package com.example.ingame.ui.fragments.catalogue

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CataloguePresenter(private val router: Router): MvpPresenter<CatalogueView>() {

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}