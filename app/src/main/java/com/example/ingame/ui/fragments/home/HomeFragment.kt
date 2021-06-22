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
import com.example.ingame.ui.adapters.viewpagers.GamesListAdapter
import com.example.ingame.ui.adapters.viewpagers.HotGamesAdapter
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.ui.navigation.IScreens
import com.example.ingame.utils.Constants.HOT_GAMES_DELAY
import com.example.ingame.utils.Constants.HOT_GAMES_TICK_RATE
import com.example.ingame.utils.selectTab
import com.example.ingame.utils.toast
import com.example.ingame.utils.unselectTab
import com.github.terrakok.cicerone.Router
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : MvpAppCompatFragment(), HomeView, BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var retrofitRepositoryImpl: RetrofitRepositoryImpl

    @Inject
    lateinit var screens: IScreens

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    private val homePresenter by moxyPresenter {
        HomePresenter(
            uiScheduler,
            retrofitRepositoryImpl,
            router,
            screens
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupGamesViewPager()
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
        binding.vpGames.currentItem = 0
    }

    override fun backPressed() = homePresenter.backPressed()
}