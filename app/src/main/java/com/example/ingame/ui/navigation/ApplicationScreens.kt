package com.example.ingame.ui.navigation

import com.example.ingame.ui.fragments.catalogue.CatalogueFragment
import com.example.ingame.ui.fragments.collections.CollectionsFragment
import com.example.ingame.ui.fragments.game.GameFragment
import com.example.ingame.ui.fragments.home.HomeFragment
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.fragments.profile.ProfileFragment
import com.example.ingame.ui.fragments.search.SearchFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ApplicationScreens : IScreens {

    override fun home() = FragmentScreen { HomeFragment.newInstance() }

    override fun catalogue() = FragmentScreen { CatalogueFragment.newInstance() }

    override fun collections() = FragmentScreen { CollectionsFragment.newInstance() }

    override fun profile() = FragmentScreen { ProfileFragment.newInstance() }

    override fun hotGames(hotGameId: Int) =
        FragmentScreen { HotGameFragment.newInstance(hotGameId = hotGameId) }

    override fun games(gameId: Int) = FragmentScreen { GameFragment.newInstance(gameId = gameId) }

    override fun search() = FragmentScreen { SearchFragment.newInstance() }
}