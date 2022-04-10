@file:Suppress("unused", "unused")

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
import com.example.ingame.utils.toast
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.mig35.carousellayoutmanager.CenterScrollListener
import moxy.ktx.moxyPresenter
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
        arguments?.getSerializable(GAME_DETAILS)!! as GameDetails?
    }

    private val aboutPresenter by moxyPresenter {
        aboutPresenterFactory.create(gameDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_about, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.gameInfo = gameDetails
    }

    override fun setSnapshots(snapshots: List<ScreenshotsResult>) {
        val carouselLayoutManager =
            CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true).also {
                it.setPostLayoutListener(CarouselZoomPostLayoutListener(0.3f))
            }
        val snapshotsAdapter = SnapshotsAdapter(snapshots)
        binding.rvSnapshots.apply {
            adapter = snapshotsAdapter
            layoutManager = carouselLayoutManager
            setHasFixedSize(true)
            addOnScrollListener(CenterScrollListener())
        }
    }

    override fun showError() = toast(getString(R.string.game_info_error))

}