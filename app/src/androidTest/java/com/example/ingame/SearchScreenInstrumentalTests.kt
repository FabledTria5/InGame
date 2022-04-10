package com.example.ingame

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ingame.screens.SearchScreen
import com.example.ingame.ui.fragments.search.SearchFragment
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenInstrumentalTests : TestCase() {

    lateinit var scenario: FragmentScenario<SearchFragment>

    private val searchScreen = SearchScreen()
    private val testString = "God of war"

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_InGame)
    }

    @Test
    fun testSearchScreenSetup() = run {
        step(description = "Check if games list is visible and empty") {
            searchScreen {
                searchResults {
                    isVisible()
                    hasSize(size = 0)
                }
            }
        }
        step(description = "Check if loading animation is invisible") {
            searchScreen {
                loadingAnimation {
                    isInvisible()
                }
            }
        }
    }

    @Test
    fun testIntentCallWhenClickOnSearchViewEndIcon() = before {
        Intents.init()
    }.after {
        Intents.release()
    }.run {
        searchScreen {
            searchView {
                click(GeneralLocation.CENTER_RIGHT)
            }
            speechIntent {
                intended()
            }
        }
    }

    @Test
    fun testIntentResultApplyToSearchView() = before {
        Intents.init()
    }.after {
        Intents.release()
    }.run {
        step(description = "Start speech intent") {
            searchScreen {
                speechIntentWithResult.intending()
                searchView {
                    click(GeneralLocation.CENTER_RIGHT)
                }
            }
        }
        step(description = "Check value in search field") {
            searchScreen {
                searchEditText {
                    hasText(text = testString)
                }
            }
        }
    }

    @Test
    fun testStartLoadingAfterApplyingStringToSearchView() {
        searchScreen {
            searchEditText {
                typeText(text = testString)
            }
            loadingAnimation {
                isVisible()
            }
            searchResults {
                isInvisible()
            }
        }
    }

    @Test
    fun testLoadingStateAfterGetSpeechResult() = before {
        Intents.init()
    }.after {
        Intents.release()
    }.run {
        step(description = "Start speech intent") {
            searchScreen {
                speechIntentWithResult.intending()
                searchView {
                    click(GeneralLocation.CENTER_RIGHT)
                }
            }
        }
        step(description = "Check loading state") {
            searchScreen {

                loadingAnimation {
                    isVisible()
                }
                searchResults {
                    isInvisible()
                }
            }
        }
    }

    @Test
    fun testLoadingStateAfterGetEmptySpeechResult() = before {
        Intents.init()
    }.after {
        Intents.release()
    }.run {
        step(description = "Set initial value for search field") {
            searchScreen {
                searchEditText {
                    typeText(testString)
                }
            }
        }
        step(description = "Start speech intent") {
            searchScreen {
                emptySpeechIntent.intending()
                searchView {
                    click(GeneralLocation.CENTER_RIGHT)
                }
            }
        }
        step(description = "Check if search field not become empty") {
            searchScreen {
                searchEditText {
                    hasText(text = testString)
                }
            }
        }
        step(description = "Check loading animation when received empty string from speech intent") {
            searchScreen {
                loadingAnimation {
                    isInvisible()
                }
            }
        }
    }

}