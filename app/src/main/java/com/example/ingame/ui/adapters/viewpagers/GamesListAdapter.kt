package com.example.ingame.ui.adapters.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ingame.ui.fragments.new_games.NewGamesFragment
import com.example.ingame.ui.fragments.popular.PopularFragment
import com.example.ingame.ui.fragments.recommended.RecommendedFragment

class GamesListAdapter(
    private val fragments: ArrayList<Fragment> = arrayListOf(
        RecommendedFragment(),
        PopularFragment(),
        NewGamesFragment()
    ), lifecycle: Lifecycle,
    fragmentManager: FragmentManager
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = fragments.count()

    override fun createFragment(position: Int) = fragments[position]
}