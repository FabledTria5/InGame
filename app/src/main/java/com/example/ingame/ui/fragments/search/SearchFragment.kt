package com.example.ingame.ui.fragments.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.ingame.R
import com.example.ingame.databinding.FragmentSearchBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.ktx.moxyPresenter
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : BaseDaggerFragment(), SearchView, BackButtonListener {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var searchPresenterFactory: SearchPresenterFactory

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

    private lateinit var binding: FragmentSearchBinding

    private val searchPresenter by moxyPresenter {
        searchPresenterFactory.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_search, container, false)
        searchPresenter.onCreateView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchPresenter.onViewCreated()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> searchPresenter.backPressed()
        else -> super.onOptionsItemSelected(item)
    }

    override fun setupListeners() {
        binding.tilSearchView.setEndIconOnClickListener {
            searchPresenter.onVoiceSearchClicked()
        }

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            binding.tieSearchView.addTextChangedListener(
                afterTextChanged = { editable ->
                    subscriber.onNext(editable.toString())
                }
            )
        })
            .map { text -> text.lowercase(Locale.getDefault()).trim() }
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { text -> text.isNotBlank() }
            .subscribeBy(
                onNext = (searchPresenter::onSearchQueryChanged),
                onError = (Timber::e)
            )
    }

    override fun setupMenu() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
    }

    override fun openDisplaySpeechRecognizer() =
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }.let(resultLauncher::launch)


    override fun showError() = Unit

    override fun backPressed() = searchPresenter.backPressed()
}