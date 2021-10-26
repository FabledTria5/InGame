package com.example.ingame.screens

import com.example.ingame.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen

class SearchScreen : Screen<SearchScreen>() {
    val searchView = KTextInputLayout { withId(R.id.tilSearchView) }
    val searchResults = KRecyclerView({ withId(R.id.rvSearchResults) }, itemTypeBuilder = {})
    val loadingAnimation = KView { withId(R.id.lottieLoadingAnimation) }
}