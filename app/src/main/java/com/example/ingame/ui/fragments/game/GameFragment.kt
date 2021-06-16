package com.example.ingame.ui.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.MainApplication
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGameBinding
import com.example.ingame.ui.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class GameFragment : MvpAppCompatFragment(), GameView, BackButtonListener {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var binding: FragmentGameBinding

    private val gamePresenter by moxyPresenter { GamePresenter(MainApplication.Navigation.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun backPressed() = gamePresenter.backPressed()

}