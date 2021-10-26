package com.example.ingame

import android.speech.RecognizerIntent
import androidx.test.espresso.action.GeneralLocation
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ingame.screens.SearchScreen
import com.example.ingame.ui.activities.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.intent.KIntent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenInstrumentalTests : TestCase() {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val searchScreen = SearchScreen()

    @Test
    fun testSearchScreenSetup() = run {
        step("Check if games list is visible and empty") {
            searchScreen {
                searchResults {
                    isVisible()
                    hasSize(size = 0)
                }
            }
        }
        step("Check if loading animation is invisible") {
            searchScreen {
                loadingAnimation {
                    isInvisible()
                }
            }
        }
    }

    @Test
    fun testIntentCallWhenClickOnSearchViewEndIcon() {
        searchScreen {
            searchView {
                click(GeneralLocation.CENTER_RIGHT)
                val searchScreenIntent = KIntent {
                    hasExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "")
                    hasExtra(RecognizerIntent.LANGUAGE_MODEL_FREE_FORM, "")
                }
                searchScreenIntent.intended()
            }
        }
    }

}