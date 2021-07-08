package com.example.ingame.ui.fragments.requirements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentRequirementsBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.utils.arguments
import com.example.ingame.utils.toast
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RequirementsFragment : BaseDaggerFragment(), RequirementsView {

    companion object {

        private const val REQUIREMENTS_MIN = "RequirementsMin"
        private const val REQUIREMENTS_REC = "RequirementsRec"

        fun newInstance(requirementsMin: String, requirementsRec: String) =
            RequirementsFragment().arguments(
                REQUIREMENTS_MIN to requirementsMin,
                REQUIREMENTS_REC to requirementsRec
            )
    }

    @Inject
    lateinit var requirementsPresenterFactory: RequirementsPresenterFactory

    private lateinit var binding: FragmentRequirementsBinding

    private val requirementsMin by lazy { arguments?.getString(REQUIREMENTS_MIN) ?: "" }
    private val requirementsRec by lazy { arguments?.getString(REQUIREMENTS_REC) ?: "" }

    @Suppress("unused")
    private val requirementsPresenter by moxyPresenter {
        requirementsPresenterFactory.create(Pair(requirementsMin, requirementsRec))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_requirements, container, false)
        return binding.root
    }

    override fun showRequirements(requirements: Pair<String, String>) = binding.run {
        minimum = requirements.first
        recommended = requirements.second
        dataLoaded = true
    }

    override fun showEmptyRequirements() = binding.run {
        dataIsEmpty = true
    }

    override fun showError() = toast(getString(R.string.reqs_error))

}