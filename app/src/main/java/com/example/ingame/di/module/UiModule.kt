package com.example.ingame.di.module

import com.example.ingame.ui.activities.main.MainActivity
import com.example.ingame.ui.fragments.about.AboutFragment
import com.example.ingame.ui.fragments.calendar.CalendarFragment
import com.example.ingame.ui.fragments.catalogue.CatalogueFragment
import com.example.ingame.ui.fragments.collections.CollectionsFragment
import com.example.ingame.ui.fragments.game.GameFragment
import com.example.ingame.ui.fragments.home.HomeFragment
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.fragments.info.InfoFragment
import com.example.ingame.ui.fragments.new_games.NewGamesFragment
import com.example.ingame.ui.fragments.popular.PopularFragment
import com.example.ingame.ui.fragments.profile.ProfileFragment
import com.example.ingame.ui.fragments.recommended.RecommendedFragment
import com.example.ingame.ui.fragments.requirements.RequirementsFragment
import com.example.ingame.ui.fragments.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindHotGamesFragment(): HotGameFragment

    @ContributesAndroidInjector
    abstract fun bindCollectionsFragment(): CollectionsFragment

    @ContributesAndroidInjector
    abstract fun bindGameFragment(): GameFragment

    @ContributesAndroidInjector
    abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun bindCatalogueFragment(): CatalogueFragment

    @ContributesAndroidInjector
    abstract fun bindCalendarFragment(): CalendarFragment

    @ContributesAndroidInjector
    abstract fun bindAboutFragment(): AboutFragment

    @ContributesAndroidInjector
    abstract fun bindInfoFragment(): InfoFragment

    @ContributesAndroidInjector
    abstract fun bindRequiredFragment(): RequirementsFragment

    @ContributesAndroidInjector
    abstract fun bindRecommendedFragment(): RecommendedFragment

    @ContributesAndroidInjector
    abstract fun bindPopularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun bindNewGamesFragment(): NewGamesFragment

    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment

}