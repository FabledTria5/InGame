package com.example.ingame.ui.fragments.hot_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentHotGameBinding
import moxy.MvpAppCompatFragment

class HotGameFragment : MvpAppCompatFragment() {

    companion object {
        fun newInstance() = HotGameFragment()
    }

    private lateinit var binding: FragmentHotGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot_game, container, false)
        return binding.root
    }

}