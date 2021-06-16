package com.example.ingame.ui.navigation

import com.example.ingame.ui.fragments.catalogue.CatalogueFragment
import com.example.ingame.ui.fragments.collections.CollectionsFragment
import com.example.ingame.ui.fragments.home.HomeFragment
import com.example.ingame.ui.fragments.profile.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ApplicationScreens : IScreens {

    override fun home() = FragmentScreen { HomeFragment.newInstance() }

    override fun catalogue() = FragmentScreen { CatalogueFragment.newInstance() }

    override fun collections() = FragmentScreen { CollectionsFragment.newInstance() }

    override fun profile() = FragmentScreen { ProfileFragment.newInstance() }

}