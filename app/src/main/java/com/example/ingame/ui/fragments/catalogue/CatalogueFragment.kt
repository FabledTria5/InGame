package com.example.ingame.ui.fragments.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCatalogueBinding
import com.example.ingame.ui.navigation.BackButtonListener
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class CatalogueFragment : MvpAppCompatFragment(), CatalogueView, BackButtonListener {

    @Inject
    lateinit var router: Router

    companion object {
        fun newInstance() = CatalogueFragment()
    }

    private lateinit var binding: FragmentCatalogueBinding

    private val cataloguePresenter by moxyPresenter {
        CataloguePresenter(router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalogue, container, false)
        return binding.root
    }

    override fun backPressed() = cataloguePresenter.backPressed()
}