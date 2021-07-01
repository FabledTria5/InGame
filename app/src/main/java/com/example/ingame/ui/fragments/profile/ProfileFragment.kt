package com.example.ingame.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentProfileBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ProfileFragment : BaseDaggerFragment(), BackButtonListener, ProfileView {

    @Inject
    lateinit var profilePresenterFactory: ProfilePresenterFactory

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: FragmentProfileBinding

    private val profilePresenter by moxyPresenter {
        profilePresenterFactory.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun backPressed() = profilePresenter.backClicked()
}