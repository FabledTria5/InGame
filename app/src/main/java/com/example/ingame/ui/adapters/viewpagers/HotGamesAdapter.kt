package com.example.ingame.ui.adapters.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.ingame.ui.fragments.hot_game.HotGameFragment

@Suppress("DEPRECATION")
class HotGamesAdapter(
    gameIds: List<Int>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragments = arrayListOf<Fragment>()

    init {
        gameIds.forEach { gameId ->
            fragments.add(HotGameFragment.newInstance(gameId))
        }
    }

    override fun getCount(): Int = fragments.count()

    override fun getItem(position: Int) = fragments[position]

}