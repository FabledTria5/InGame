package com.example.ingame.ui.fragments.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCollectionsBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CollectionsFragment : BaseDaggerFragment(), CollectionsView, BackButtonListener {

    @Inject
    lateinit var router: Router

    companion object {
        fun newInstance() = CollectionsFragment()
    }

    private lateinit var binding: FragmentCollectionsBinding

    private val collectionPresenter by moxyPresenter {
        CollectionPresenter(router)
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