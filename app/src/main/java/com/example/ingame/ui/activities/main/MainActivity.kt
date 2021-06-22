package com.example.ingame.ui.activities.main

import android.os.Bundle
import android.view.MenuItem
import com.example.ingame.R
import com.example.ingame.databinding.ActivityMainBinding
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.ui.navigation.IScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView, NavigationBarView.OnItemSelectedListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var binding: ActivityMainBinding

    private val mainPresenter by moxyPresenter {
        MainPresenter(router, screens)
    }

    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        savedInstanceState
            ?: router.newRootScreen(screens.home())
        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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