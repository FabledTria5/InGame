package com.example.ingame.ui.fragments.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.MvpApplication
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCollectionsBinding
import com.example.ingame.ui.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CollectionsFragment : MvpAppCompatFragment(), CollectionsView, BackButtonListener {

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private lateinit var binding: FragmentCollectionsBinding

    private val collectionPresenter by moxyPresenter {
        CollectionPresenter(MvpApplication.Navigation.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_collections, container, false)
        return binding.root
    }

    override fun backPressed() = collectionPresenter.backPressed()
}