package com.example.ingame.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager.widget.ViewPager.SCROLL_STATE_DRAGGING
import com.example.ingame.R
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.databinding.FragmentHomeBinding
import com.example.ingame.ui.di_base.BaseDaggerFragment
import com.example.ingame.ui.adapters.viewpagers.GamesListAdapter
import com.example.ingame.ui.adapters.viewpagers.HotGamesAdapter
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.ui.schedulers.Schedulers
import com.example.ingame.utils.Constants.HOT_GAMES_DELAY
import com.example.ingame.utils.Constants.HOT_GAMES_TICK_RATE
import com.example.ingame.utils.selectTab
import com.example.ingame.utils.toast
import com.example.ingame.utils.unselectTab
import com.github.terrakok.cicerone.Router
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class HomeFragment : BaseDaggerFragment(), HomeView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var retrofitRepositoryImpl: RetrofitRepositoryImpl

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    private val homePresenter by moxyPresenter {
        HomePresenter(
            schedulers,
            retrofitRepositoryImpl,
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
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

    override fun setupSlider(arrayList: ArrayList<HotGameFragment>) {
        binding.vpHotGames.apply {
            setInterval(HOT_GAMES_TICK_RATE)
            setScrollDurationFactor(2.0)
            adapter = HotGamesAdapter(arrayList, childFragmentManager)
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
    }

    override fun setupGamesViewPager() {
        binding.vpGames.adapter =
            GamesListAdapter(lifecycle = lifecycle, fragmentManager = childFragmentManager)

        TabLayoutMediator(binding.tabLayout, binding.vpGames) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.recommended)
                1 -> tab.setText(R.string.popular)
                2 -> tab.setText(R.string.new_tab)
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) =
                homePresenter.onGamesPageSelected(tab?.position)

            override fun onTabUnselected(tab: TabLayout.Tab?) =
                homePresenter.onGamesPageUnselected(tab?.position)

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    override fun selectPageText(page: Int?) {
        page?.let { binding.tabLayout.getTabAt(it)?.selectTab() }
    }

    override fun unselectPageText(page: Int?) {
        page?.let { binding.tabLayout.getTabAt(it)?.unselectTab() }
    }

    override fun backPressed() = homePresenter.backPressed()
}