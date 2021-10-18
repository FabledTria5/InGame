package com.example.ingame.ui.activities.main

import android.os.Bundle
import android.view.MenuItem
import com.example.ingame.R
import com.example.ingame.databinding.ActivityMainBinding
import com.example.ingame.ui.di_base.BaseDaggerActivity
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.ui.navigation.IScreens
import com.example.ingame.utils.clearGradient
import com.example.ingame.utils.getTextView
import com.example.ingame.utils.setGradientText
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationBarView
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : BaseDaggerActivity(), MainView, NavigationBarView.OnItemSelectedListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var mainPresenterFactory: MainPresenterFactory

    private lateinit var binding: ActivityMainBinding

    private val mainPresenter by moxyPresenter {
        mainPresenterFactory.create()
    }

    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        savedInstanceState
            ?: router.newRootScreen(screens.home())
        binding.bottomNavigation.setOnItemSelectedListener(this)
        binding.bottomNavigation.itemIconTintList = null
        mainPresenter.onCreate()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun setBottomNavTextGradient(position: Int) =
        binding.bottomNavigation.getTextView(position).setGradientText()

    override fun clearBottomNavText(position: Int) =
        binding.bottomNavigation.getTextView(position).clearGradient()

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is BackButtonListener && fragment.backPressed()) return
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