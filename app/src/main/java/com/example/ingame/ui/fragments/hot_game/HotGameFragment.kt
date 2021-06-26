package com.example.ingame.ui.fragments.hot_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.databinding.FragmentHotGameBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.arguments
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HotGameFragment : BaseDaggerFragment(), HotGameView {

    companion object {
        private const val GAME_INFO = "game_info"

        fun newInstance(gameInfo: Result) = HotGameFragment().arguments(
            GAME_INFO to gameInfo,
        )
    }

    @Inject
    lateinit var hotGamePresenterFactory: HotGamesPresenterFactory

    private lateinit var binding: FragmentHotGameBinding

    private val gameInfo by lazy {
        arguments?.getSerializable(GAME_INFO)!! as Result
    }

    private val hotGamePresenter by moxyPresenter {
        hotGamePresenterFactory.create(gameInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_hot_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.root.setOnClickListener {
            hotGamePresenter.onGameClicked()
        }
    }

    override fun setGameInfo(gameInfo: Result) {
        binding.gameInfo = gameInfo
    }

}