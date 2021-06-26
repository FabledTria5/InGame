package com.example.ingame.ui.fragments.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCatalogueBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogueFragment : BaseDaggerFragment(), CatalogueView, BackButtonListener {

    @Inject
    lateinit var cataloguePresenterFactory: CataloguePresenterFactory

    companion object {
        fun newInstance() = CatalogueFragment()
    }

    private lateinit var binding: FragmentCatalogueBinding

    private val cataloguePresenter by moxyPresenter {
        cataloguePresenterFactory.create()
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