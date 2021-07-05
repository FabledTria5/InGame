package com.example.ingame.ui.fragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGamesListBinding
import com.example.ingame.ui.adapters.recyclerviews.home.HomeGamesListAdapter
import com.example.ingame.ui.base.GamesLoaderView
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.Constants.HOME_GAMES_LIST_SIZE
import com.example.ingame.utils.Constants.RESULT_SELECTED_PLATFORM
import com.example.ingame.utils.Constants.SELECTED_PLATFORM
import com.example.ingame.utils.GridSpacingItemDecorator
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFragmentResultListener(RESULT_SELECTED_PLATFORM) { _, bundle ->
            popularPresenter.platformSelected(bundle.getInt(SELECTED_PLATFORM))
        }
    }

    override fun setupRecycler() {
        gamesListAdapter = HomeGamesListAdapter(popularPresenter.homeGamesPresenter)
        binding.rvGamesList.apply {
            adapter = gamesListAdapter
            itemAnimator = FlipInTopXAnimator()
            itemAnimator?.changeDuration = 1000
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecorator(spacing = 60, includeEdge = true))
        }
    }

    override fun fillList() =
        gamesListAdapter.notifyItemRangeInserted(0, HOME_GAMES_LIST_SIZE)

    override fun clearList() =
        gamesListAdapter.notifyItemRangeRemoved(0, HOME_GAMES_LIST_SIZE)

}