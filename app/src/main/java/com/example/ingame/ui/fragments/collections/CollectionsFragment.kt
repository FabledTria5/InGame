package com.example.ingame.ui.fragments.collections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentCollectionsBinding
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class CollectionsFragment : Fragment(), CollectionsView {

    private lateinit var binding: FragmentCollectionsBinding

    @InjectPresenter
    lateinit var collectionPresenter: CollectionPresenter

    @ProvidePresenter
    fun providePresenter() = CollectionPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_collections, container, false)
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = CollectionsFragment().apply {

        }
    }
}