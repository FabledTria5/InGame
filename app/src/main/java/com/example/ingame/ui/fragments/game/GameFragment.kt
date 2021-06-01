package com.example.ingame.ui.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.MvpApplication
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGameBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class GameFragment : MvpAppCompatFragment(), GameView {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var binding: FragmentGameBinding

    private val gamePresenter by moxyPresenter { GamePresenter(MvpApplication.Navigation.router) }

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