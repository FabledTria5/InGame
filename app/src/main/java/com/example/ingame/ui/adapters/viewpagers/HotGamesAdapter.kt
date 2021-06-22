package com.example.ingame.ui.adapters.viewpagers

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import moxy.MvpAppCompatFragment

@Suppress("DEPRECATION")
class HotGamesAdapter(
    private val fragments: List<MvpAppCompatFragment>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = fragments.count()

    override fun getItem(position: Int) = fragments[position]

}