package com.example.ingame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.ingame.screens.HomeScreen
import com.example.ingame.ui.activities.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenInstrumentalTests : TestCase() {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val homeScreen = HomeScreen()

    @Test
    fun testingHomeScreenSetupBehavior() = before { }.after { }.run {
        step("Is loading for hot games visible by default") {
            homeScreen {
                progressBar {
                    isVisible()
                }
            }
        }
        step("Is hot games viewpager at page 0") {
            homeScreen {
                hotGamesPager {
                    isAtPage(0)
                }
            }
        }
        step("Is tablayout at first page") {
            homeScreen {
                tabLayout {
                    isTabSelected(0)
                }
            }
        }
    }

}