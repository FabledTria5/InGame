package com.example.ingame.ui.adapters.viewpagers

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import moxy.MvpAppCompatFragment

class HotGamesAdapter(
    private val fragments: List<MvpAppCompatFragment>,
    lifecycle: Lifecycle,
    fragmentManager: FragmentManager
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragments.count()

    override fun createFragment(position: Int) = fragments[position]

}