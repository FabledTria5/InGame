package com.example.ingame.ui.fragments.hot_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.databinding.FragmentHotGameBinding
import com.example.ingame.ui.fragments.home.OnGameClickListener
import com.example.ingame.ui.navigation.IScreens
import com.example.ingame.utils.arguments
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class HotGameFragment : MvpAppCompatFragment(), HotGameView {

    companion object {
        private const val GAME_INFO = "game_info"
        private const val GAME_CLICK_LISTENER = "listener"

        fun newInstance(gameInfo: Result, onGameClickListener: OnGameClickListener) =
            HotGameFragment().arguments(
                GAME_INFO to gameInfo,
                GAME_CLICK_LISTENER to onGameClickListener
            )
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    private lateinit var binding: FragmentHotGameBinding

    private val gameInfo by lazy {
        arguments?.getSerializable(GAME_INFO)!! as Result
    }

    private val onGameClickListener by lazy {
        arguments?.getSerializable(GAME_CLICK_LISTENER) as OnGameClickListener
    }

    private val hotGamePresenter by moxyPresenter {
        HotGamePresenter(gameInfo, router, screens)
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
            router.navigateTo(screens.games(gameInfo.id))
        }
    }

    override fun setGameInfo(gameInfo: Result) {
        binding.gameInfo = gameInfo
    }

}