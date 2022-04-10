package com.example.ingame.screens

import com.example.ingame.R
import io.github.kakaocup.kakao.pager.KViewPager
import io.github.kakaocup.kakao.progress.KProgressBar
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.tabs.KTabLayout

class HomeScreen : Screen<HomeScreen>() {
    val progressBar = KProgressBar { withId(R.id.progressBar) }
    val hotGamesPager = KViewPager { withId(R.id.vpHotGames) }
    val tabLayout = KTabLayout { withId(R.id.tabLayout) }
}