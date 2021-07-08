package com.example.ingame.ui.fragments.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.game_developers.GameDevelopers
import com.example.ingame.databinding.FragmentInfoBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.arguments
import com.example.ingame.utils.toast
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class InfoFragment : BaseDaggerFragment(), InfoView {

    companion object {
        const val GAME_DETAILS = "gameDetails"

        fun newInstance(gameDetails: GameDetails) =
            InfoFragment().arguments(GAME_DETAILS to gameDetails)
    }

    @Inject
    lateinit var infoPresenterFactory: InfoPresenterFactory

    private val gameDetails by lazy {
        arguments?.getSerializable(GAME_DETAILS) as GameDetails?
    }

    private val infoPresenter by moxyPresenter {
        infoPresenterFactory.create(gameDetails)
    }

    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun setInfo(gameDevelopers: GameDevelopers) = binding.run {
        gameInfo = gameDetails
        developers = gameDevelopers
        infoLoaded = true
    }

    override fun showError() = toast(getString(R.string.game_info_error))
}