package com.example.ingame.ui.fragments.catalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCatalogueBinding
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class CatalogueFragment : MvpAppCompatFragment(), CatalogueView {

    private lateinit var binding: FragmentCatalogueBinding

    @InjectPresenter
    lateinit var cataloguePresenter: CataloguePresenter

    @ProvidePresenter
    fun providePresenter() = CataloguePresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalogue, container, false)
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = CatalogueFragment().apply {

        }
    }
}