package com.example.ingame.ui.adapters.viewpagers

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ingame.ui.fragments.games_list.GamesListFragment
import moxy.MvpAppCompatFragment

class GamesListAdapter(
    private val fragments: ArrayList<MvpAppCompatFragment> = arrayListOf(
        GamesListFragment(),
        GamesListFragment(),
        GamesListFragment()
    ), lifecycle: Lifecycle,
    fragmentManager: FragmentManager
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragments.count()

    override fun createFragment(position: Int) = fragments[position]
}