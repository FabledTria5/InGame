package com.example.ingame.ui.fragments.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.network.model.screenshots.ScreenshotsResult
import com.example.ingame.databinding.FragmentAboutBinding
import com.example.ingame.ui.adapters.recyclerviews.SnapshotsAdapter
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.arguments
import moxy.ktx.moxyPresenter
import recycler.coverflow.CoverFlowLayoutManger
import javax.inject.Inject

class AboutFragment : BaseDaggerFragment(), AboutView {

    companion object {
        private const val GAME_DETAILS = "gameDetails"

        fun newInstance(gameDetails: GameDetails) =
            AboutFragment().arguments(GAME_DETAILS to gameDetails)
    }

    @Inject
    lateinit var aboutPresenterFactory: AboutPresenterFactory

    private lateinit var binding: FragmentAboutBinding

    private val gameDetails by lazy {
        arguments?.getSerializable(GAME_DETAILS)!! as GameDetails
    }

    private val aboutPresenter by moxyPresenter {
        aboutPresenterFactory.create(gameDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_about, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.gameInfo = gameDetails
    }

    override fun setSnapshots(snapshots: List<ScreenshotsResult>) {
        val coverFlowLayoutManger =
            CoverFlowLayoutManger(false, false, false, 0.7f)
        val snapshotsAdapter = SnapshotsAdapter(snapshots)
        binding.rvSnapshots.apply {
            adapter = snapshotsAdapter
            layoutManager = coverFlowLayoutManger
        }
        binding.rvSnapshots.scrollToPosition(snapshotsAdapter.itemCount / 2)
    }

}