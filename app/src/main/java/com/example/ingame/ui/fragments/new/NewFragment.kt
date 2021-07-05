package com.example.ingame.ui.fragments.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentGamesListBinding
import com.example.ingame.ui.base.GamesLoaderView
import com.example.ingame.ui.di_base.BaseDaggerFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class NewFragment : BaseDaggerFragment(), GamesLoaderView {

    @Inject
    lateinit var newPresenterFactory: NewPresenterFactory

    private lateinit var binding: FragmentGamesListBinding

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

    override fun setupRecycler() {

    }

    override fun fillList() {

    }

    override fun clearList() {

    }

}