package com.example.ingame.ui.fragments.catalogue

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
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

    private var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val spokenText =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let {
                    it[0]
                }
            binding.tieSearchView.setText(spokenText)
        }
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cataloguePresenter.onViewCreated()
    }

    override fun setupListeners() {
        binding.tilSearchView.setEndIconOnClickListener {
            cataloguePresenter.onVoiceSearchClicked()
        }
    }

    override fun openDisplaySpeechRecognizer() =
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }.let(resultLauncher::launch)

    override fun backPressed() = cataloguePresenter.backPressed()
}