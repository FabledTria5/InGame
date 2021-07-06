package com.example.ingame.ui.fragments.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager.widget.ViewPager.SCROLL_STATE_DRAGGING
import com.example.ingame.R
import com.example.ingame.databinding.FragmentHomeBinding
import com.example.ingame.ui.adapters.viewpagers.GamesListAdapter
import com.example.ingame.ui.adapters.viewpagers.HotGamesAdapter
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.utils.Constants.HOT_GAMES_DELAY
import com.example.ingame.utils.Constants.HOT_GAMES_TICK_RATE
import com.example.ingame.utils.Constants.PLATFORM_REQUEST
import com.example.ingame.utils.Constants.PREFERENCE_DATE
import com.example.ingame.utils.Constants.RESULT_SELECTED_PLATFORM
import com.example.ingame.utils.Constants.SELECTED_PLATFORM
import com.example.ingame.utils.selectTab
import com.example.ingame.utils.toast
import com.example.ingame.utils.unselectTab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HomeFragment : BaseDaggerFragment(), HomeView, BackButtonListener {

    @Inject
    lateinit var homePresenterFactory: HomePresenterFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    private val homePresenter by moxyPresenter {
        homePresenterFactory.create()
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
        childFragmentManager.setFragmentResultListener(
            PLATFORM_REQUEST,
            viewLifecycleOwner
        ) { _, _ ->
            onInitPlatformSelect()
        }
    }

    override fun onPause() {
        binding.vpHotGames.stopAutoScroll()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.vpHotGames.startAutoScroll(HOT_GAMES_DELAY)
    }

    override fun updateHotGames(newPosition: Int) =
        binding.vpHotGames.setCurrentItem(newPosition, true)

    override fun setNewSliderItem(previousTab: Int, newTab: Int) {
        binding.tabLayout.apply {
            getTabAt(previousTab)?.unselectTab()
            getTabAt(newTab)?.selectTab()
        }
    }

    override fun showError() = toast("Error while receiving games. Try again later")

    override fun updateDate(newDate: String) {
        sharedPreferences.edit().apply {
            putString(PREFERENCE_DATE, newDate)
            apply()
        }
    }

    override fun setupSlider(hotGamesIds: List<Int>) {
        binding.vpHotGames.apply {
            setInterval(HOT_GAMES_TICK_RATE)
            setScrollDurationFactor(2.0)
            adapter = HotGamesAdapter(hotGamesIds, childFragmentManager)
            addOnPageChangeListener(object : OnPageChangeListener {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    homePresenter.apply {
                        if (wasDragging) {
                            startAutoScroll()
                            wasDragging = !wasDragging
                        }
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                    if (state == SCROLL_STATE_DRAGGING) {
                        stopAutoScroll()
                        homePresenter.wasDragging = true
                    }
                }
            })
        }
        binding.indicator.setViewPager(binding.vpHotGames)
        binding.hotGamesLoaded = true
    }

    override fun setupPlatformsList(platforms: List<String>) {
        binding.actvPlatforms.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.spinner_item_platforms,
                platforms.toTypedArray()
            )
        )
        binding.actvPlatforms.setText(
            binding.actvPlatforms.adapter.getItem(homePresenter.platformsListPosition).toString(),
            false
        )

        onInitPlatformSelect()

        binding.actvPlatforms.apply {
            setOnItemClickListener { _, _, itemPosition, _ ->
                homePresenter.onPlatformSelected(
                    itemPosition,
                    adapter.getItem(itemPosition).toString()
                )
                binding.actvPlatforms.clearFocus()
            }
        }
    }

    override fun setCurrentPlatform(platformId: Int) = childFragmentManager.setFragmentResult(
        RESULT_SELECTED_PLATFORM,
        bundleOf(SELECTED_PLATFORM to platformId)
    )

    override fun setupGamesViewPager() {
        binding.vpGames.adapter =
            GamesListAdapter(lifecycle = lifecycle, fragmentManager = childFragmentManager)

        TabLayoutMediator(binding.tabLayout, binding.vpGames) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.recommended)
                1 -> tab.text = getString(R.string.popular)
                2 -> tab.text = getString(R.string.new_tab)
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                homePresenter.onGamesPageSelected(tab?.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                homePresenter.onGamesPageUnselected(tab?.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    override fun selectPageText(page: Int) {
        binding.tabLayout.getTabAt(page)?.selectTab()
    }

    override fun unselectPageText(page: Int) {
        binding.tabLayout.getTabAt(page)?.unselectTab()
    }

    override fun backPressed() = homePresenter.backPressed()

    private fun onInitPlatformSelect() = homePresenter.onPlatformSelected(
        homePresenter.platformsListPosition,
        binding.actvPlatforms.adapter.getItem(homePresenter.platformsListPosition).toString()
    )
}