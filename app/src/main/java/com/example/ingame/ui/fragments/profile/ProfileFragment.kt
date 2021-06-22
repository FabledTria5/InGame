package com.example.ingame.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentProfileBinding
import com.example.ingame.ui.navigation.BackButtonListener
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : MvpAppCompatFragment(), BackButtonListener, ProfileView {

    @Inject
    lateinit var router: Router

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: FragmentProfileBinding

    private val profilePresenter by moxyPresenter {
        ProfilePresenter(router)
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