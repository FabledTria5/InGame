package com.example.ingame.ui.fragments.recommended

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGamesListBinding
import com.example.ingame.ui.base.GamesLoaderView
import com.example.ingame.ui.di_base.BaseDaggerFragment

class RecommendedFragment : BaseDaggerFragment(), GamesLoaderView {

    private lateinit var binding: FragmentGamesListBinding

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
        binding.rvGamesList.visibility = View.GONE
        binding.tvRecommendationsMessage.visibility = View.VISIBLE
    }

    override fun setupRecycler() {

    }

    override fun fillList() {

    }

    override fun clearList() {

    }

}