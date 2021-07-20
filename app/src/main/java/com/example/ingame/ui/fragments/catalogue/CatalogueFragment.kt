package com.example.ingame.ui.fragments.catalogue

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCatalogueBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
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
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_catalogue, container, false)
        cataloguePresenter.onCreateView()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.catalogue_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.catalogueSearch -> cataloguePresenter.onSearchClicked()
        else -> super.onOptionsItemSelected(item)
    }

    override fun backPressed() = cataloguePresenter.backPressed()

    override fun setupMenu() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
    }
}