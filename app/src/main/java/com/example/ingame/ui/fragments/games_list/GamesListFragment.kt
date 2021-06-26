package com.example.ingame.ui.fragments.games_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGamesListBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.adapters.recyclerviews.HomeGamesListAdapter
import com.example.ingame.utils.GridSpacingItemDecorator

class GamesListFragment : BaseDaggerFragment() {

    private lateinit var binding: FragmentGamesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_games_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvGamesList.apply {
            adapter =
                HomeGamesListAdapter().apply {
                    addGames(arrayListOf("1", "2", "3", "4", "5"))
                    notifyDataSetChanged()
                }
            addItemDecoration(GridSpacingItemDecorator(spacing = 60, includeEdge = true))
        }
    }

}