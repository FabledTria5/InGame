package com.example.ingame.ui.fragments.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.ingame.MvpApplication
import com.example.ingame.R
import com.example.ingame.databinding.FragmentHomeBinding
import com.example.ingame.ui.adapters.viewpagers.GamesListAdapter
import com.example.ingame.ui.adapters.viewpagers.HotGamesAdapter
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.utils.selectTab
import com.example.ingame.utils.unselectTab
import com.google.android.material.tabs.TabLayoutMediator
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class HomeFragment : MvpAppCompatFragment(), HomeView, BackButtonListener {

    companion object {
        fun newInstance()  = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    private val homePresenter by moxyPresenter {
        HomePresenter(HomeModel(), MvpApplication.Navigation.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTopViewPager()
        setupPlatformsMenu()
        setupGamesViewPager()
    }

    override fun onPause() {
        super.onPause()
        homePresenter.pauseTimer()
    }

    override fun onResume() {
        super.onResume()
        homePresenter.startTimer()
    }

    override fun updateHotGames() {
        Handler(Looper.getMainLooper()).post {
            binding.vpHotGames.apply {
                if (currentItem == binding.vpHotGames.adapter?.itemCount?.minus(1))
                    currentItem = 0
                else setCurrentItem(currentItem + 1, true)
            }
        }
    }

    override fun updateTab(previousTab: Int, newTab: Int) {
        binding.tabLayout.apply {
            getTabAt(previousTab)?.unselectTab()
            getTabAt(newTab)?.selectTab()
        }
    }

    private fun setupPlatformsMenu() {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.Platforms)
        ).also {
            binding.actvPlatforms.setAdapter(it)
            binding.actvPlatforms.setText(
                resources.getStringArray(R.array.Platforms).first(),
                false
            )
            binding.actvPlatforms.setOnItemClickListener { _, _, position, _ ->
                val value = it.getItem(position) ?: ""
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTopViewPager() = binding.apply {
        vpHotGames.adapter = HotGamesAdapter(getFragments(), lifecycle, childFragmentManager)
        vpHotGames.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var isScrolled = false

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    homePresenter.pauseTimer()
                    isScrolled = true
                } else if (isScrolled && state == ViewPager2.SCROLL_STATE_IDLE) {
                    homePresenter.startTimer()
                    isScrolled = false
                }
            }
        })
        indicator.setViewPager(vpHotGames)
    }

    private fun setupGamesViewPager() {
        binding.vpGames.adapter =
            GamesListAdapter(lifecycle = lifecycle, fragmentManager = childFragmentManager)

        TabLayoutMediator(binding.tabLayout, binding.vpGames) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.recommended)
                1 -> tab.setText(R.string.popular)
                else -> tab.setText(R.string.new_tab)
            }
            binding.vpGames.setCurrentItem(position, true)
        }.attach()

        binding.vpGames.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                homePresenter.tabChanged(position)
            }
        })

        binding.vpGames.currentItem = 0
    }

    private fun getFragments() = arrayListOf(
        HotGameFragment(),
        HotGameFragment(),
        HotGameFragment(),
        HotGameFragment(),
        HotGameFragment()
    )

    override fun backPressed() = homePresenter.backPressed()
}