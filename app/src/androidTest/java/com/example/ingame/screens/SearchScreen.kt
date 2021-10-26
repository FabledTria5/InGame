package com.example.ingame.screens

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.speech.RecognizerIntent
import com.example.ingame.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.intent.KIntent
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen

class SearchScreen : Screen<SearchScreen>() {
    val searchView = KTextInputLayout { withId(R.id.tilSearchView) }
    val searchEditText = KEditText { withId(R.id.tieSearchView) }
    val searchResults = KRecyclerView({ withId(R.id.rvSearchResults) }, itemTypeBuilder = {})
    val loadingAnimation = KView { withId(R.id.lottieLoadingAnimation) }
    val speechIntent = KIntent {
        hasExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }
    val speechIntentWithResult = KIntent {
        hasExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        withResult {
            withCode(RESULT_OK)
            withData(
                Intent().putStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS,
                    arrayListOf("God of war")
                )
            )
        }
    }
    val emptySpeechIntent = KIntent {
        hasExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        withResult {
            withCode(RESULT_OK)
            withData(
                Intent().putStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS,
                    arrayListOf("")
                )
            )
        }
    }
}