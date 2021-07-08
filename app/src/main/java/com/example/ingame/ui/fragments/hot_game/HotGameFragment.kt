package com.example.ingame.ui.fragments.hot_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.db.model.HotGame
import com.example.ingame.databinding.FragmentHotGameBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.arguments
import com.example.ingame.utils.toast
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HotGameFragment : BaseDaggerFragment(), HotGameView {

    companion object {
        private const val GAME_ID = "gameId"

        fun newInstance(hotGameId: Int) = HotGameFragment().arguments(
            GAME_ID to hotGameId,
        )
    }

    @Inject
    lateinit var hotGamePresenterFactory: HotGamesPresenterFactory

    private lateinit var binding: FragmentHotGameBinding

    private val hotGameId by lazy {
        arguments?.getInt(GAME_ID)!!
    }

    private val hotGamePresenter by moxyPresenter {
        hotGamePresenterFactory.create(hotGameId)
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
        view.setOnClickListener {
            hotGamePresenter.onGameClicked()
        }
    }

    override fun setGameInfo(hotGame: HotGame) {
        binding.hotGame = hotGame
    }

    override fun showError() = toast(getString(R.string.game_info_error))

}