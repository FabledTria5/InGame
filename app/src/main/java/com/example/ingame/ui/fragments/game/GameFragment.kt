package com.example.ingame.ui.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.databinding.FragmentGameBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.ui.schedulers.Schedulers
import com.example.ingame.utils.arguments
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class GameFragment : BaseDaggerFragment(), GameView, BackButtonListener {

    companion object {
        private const val GAME_ID = "game_id"

        fun newInstance(gameId: Int) = GameFragment().arguments(GAME_ID to gameId)
    }

    private lateinit var binding: FragmentGameBinding

    @Inject
    lateinit var gamePresenterFactory: GamePresenterFactory

    private val gameId by lazy { arguments?.getInt(GAME_ID)!! }

    private val gamePresenter by moxyPresenter {
        gamePresenterFactory.create(gameId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun setGameData(gameDetails: GameDetails) {
        binding.gameDetail = gameDetails
    }

    override fun backPressed() = gamePresenter.backPressed()

}