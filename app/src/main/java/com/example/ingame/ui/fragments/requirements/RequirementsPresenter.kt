package com.example.ingame.ui.fragments.requirements

import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class RequirementsPresenter @AssistedInject
constructor(@Assisted private val requirements: Pair<String, String>) :
    MvpPresenter<RequirementsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showRequirements()
    }

    private fun showRequirements() {
        if (requirements.first.isNotEmpty() and requirements.second.isNotEmpty()) {
            viewState.showRequirements(requirements)
        } else if (requirements.second.isEmpty()) {
            if (requirements.first.contains("Recommended")) {
                viewState.showRequirements(
                    Pair(
                        requirements.first.split("Recommended: ")[0],
                        requirements.first.split("Recommended: ")[1]
                    )
                )
            } else {
                viewState.showRequirements(requirements)
            }
        }
    }
}