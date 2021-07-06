package com.example.ingame.ui.fragments.new_games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGamesListBinding
import com.example.ingame.ui.adapters.recyclerviews.home.HomeGamesListAdapter
import com.example.ingame.ui.base.GamesLoaderView
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.Constants
import com.example.ingame.utils.Constants.PLATFORM_REQUEST
import com.google.android.flexbox.*
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class NewGamesFragment : BaseDaggerFragment(), GamesLoaderView {

    @Inject
    lateinit var newPresenterFactory: NewPresenterFactory

    private lateinit var binding: FragmentGamesListBinding
    private lateinit var gamesListAdapter: HomeGamesListAdapter

    private val newPresenter by moxyPresenter {
        newPresenterFactory.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_games_list, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setFragmentResult(
            PLATFORM_REQUEST,
            bundleOf(PLATFORM_REQUEST to newPresenter.selectedPlatform)
        )
        setFragmentResultListener(Constants.RESULT_SELECTED_PLATFORM) { _, bundle ->
            newPresenter.platformSelected(bundle.getInt(Constants.SELECTED_PLATFORM))
        }
    }

    override fun setupRecycler() {
        gamesListAdapter = HomeGamesListAdapter(newPresenter.homeGamesPresenter)

        val flexLayoutManager = FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP)
            .apply {
                justifyContent = JustifyContent.SPACE_AROUND
                alignItems = AlignItems.CENTER
            }

        val flexBoxItemDecoration = FlexboxItemDecoration(context)
            .apply {
                setOrientation(FlexboxItemDecoration.HORIZONTAL)
                setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.list_divider))
            }

        binding.rvGamesList.apply {
            adapter = gamesListAdapter
            itemAnimator = FlipInTopXAnimator()
            itemAnimator?.changeDuration = 1000
            layoutManager = flexLayoutManager
            addItemDecoration(flexBoxItemDecoration)
        }
    }

    override fun fillList() =
        gamesListAdapter.notifyItemRangeInserted(0, Constants.HOME_GAMES_LIST_SIZE)

    override fun clearList() =
        gamesListAdapter.notifyItemRangeRemoved(0, Constants.HOME_GAMES_LIST_SIZE)
}