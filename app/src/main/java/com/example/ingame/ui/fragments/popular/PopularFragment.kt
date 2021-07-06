package com.example.ingame.ui.fragments.popular

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
import com.example.ingame.utils.Constants.HOME_GAMES_LIST_SIZE
import com.example.ingame.utils.Constants.PLATFORM_REQUEST
import com.example.ingame.utils.Constants.RESULT_SELECTED_PLATFORM
import com.example.ingame.utils.Constants.SELECTED_PLATFORM
import com.google.android.flexbox.*
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class PopularFragment : BaseDaggerFragment(), GamesLoaderView {

    @Inject
    lateinit var popularPresenterFactory: PopularPresenterFactory

    private lateinit var binding: FragmentGamesListBinding
    private lateinit var gamesListAdapter: HomeGamesListAdapter

    private val popularPresenter by moxyPresenter {
        popularPresenterFactory.create()
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
        setFragmentResult(PLATFORM_REQUEST, bundleOf(PLATFORM_REQUEST to 0))
        setFragmentResultListener(RESULT_SELECTED_PLATFORM) { _, bundle ->
            popularPresenter.platformSelected(bundle.getInt(SELECTED_PLATFORM))
        }
    }

    override fun setupRecycler() {
        gamesListAdapter = HomeGamesListAdapter(popularPresenter.homeGamesPresenter)

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

    override fun showEmptyMessage() {
        binding.dataIsEmpty = true
    }

    override fun showList() {
        binding.dataIsEmpty = false
    }

    override fun fillList() =
        gamesListAdapter.notifyItemRangeInserted(0, HOME_GAMES_LIST_SIZE)

    override fun clearList() =
        gamesListAdapter.notifyItemRangeRemoved(0, HOME_GAMES_LIST_SIZE)
}