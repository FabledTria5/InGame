package com.example.ingame.ui.activities.main

import android.os.Bundle
import android.view.MenuItem
import com.example.ingame.MainApplication
import com.example.ingame.R
import com.example.ingame.databinding.ActivityMainBinding
import com.example.ingame.ui.navigation.ApplicationScreens
import com.example.ingame.ui.navigation.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView, NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private val mainPresenter by moxyPresenter {
        MainPresenter(MainApplication.Navigation.router, ApplicationScreens())
    }

    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        savedInstanceState
            ?: MainApplication.Navigation.router.newRootScreen(ApplicationScreens().home())
        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        MainApplication.Navigation.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        MainApplication.Navigation.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) return
        }
        mainPresenter.backClicked()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> mainPresenter.homeClicked()
            R.id.catalogue -> mainPresenter.catalogueCLicked()
            R.id.collections -> mainPresenter.collectionsClicked()
            R.id.profile -> mainPresenter.profileClicked()
        }
        return true
    }
}