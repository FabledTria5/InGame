package com.example.ingame.ui.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.databinding.FragmentGameBinding
import com.example.ingame.ui.adapters.viewpagers.GameInfoAdapter
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.fragments.about.AboutFragment
import com.example.ingame.ui.fragments.info.InfoFragment
import com.example.ingame.ui.fragments.requirements.RequirementsFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.utils.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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

    private val gameId by lazy { arguments?.getInt(GAME_ID) ?: -1 }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
    }

    override fun initViewPager(gameDetails: GameDetails) {
        binding.vpGameInfo.adapter =
            GameInfoAdapter(
                lifecycle = lifecycle,
                fragmentManager = childFragmentManager,
                fragments = arrayListOf(
                    AboutFragment.newInstance(gameDetails = gameDetails),
                    InfoFragment.newInstance(gameDetails = gameDetails),
                    RequirementsFragment.newInstance(
                        requirementsMin = gameDetails.getMinRequirements(),
                        requirementsRec = gameDetails.getRecRequirements()
                    )
                )
            )

        TabLayoutMediator(binding.tabLayout, binding.vpGameInfo) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.about)
                1 -> tab.text = getString(R.string.info)
                2 -> tab.text = getString(R.string.requirements)
            }
        }.attach()
    }

    override fun setGameData(gameDetails: GameDetails) {
        binding.gameDetail = gameDetails
    }

    override fun selectPageText(page: Int) {
        binding.tabLayout.getTabAt(page)?.selectTab()
    }

    override fun unselectPageText(page: Int) {
        binding.tabLayout.getTabAt(page)?.unselectTab()
    }

    override fun backPressed() = gamePresenter.backPressed()

    private fun setupListeners() {

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                gamePresenter.onTabSelected(tab?.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                gamePresenter.onTabUnselected(tab?.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

}