package com.example.ingame.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.ingame.R
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.databinding.FragmentHomeBinding
import com.example.ingame.ui.adapters.viewpagers.GamesListAdapter
import com.example.ingame.ui.adapters.viewpagers.HotGamesAdapter
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
import com.example.ingame.ui.navigation.BackButtonListener
import com.example.ingame.utils.selectTab
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

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: FragmentHomeBinding

    private val homePresenter by moxyPresenter {
        HomePresenter(
            uiScheduler,
            retrofitRepositoryImpl,
            HomeModel(sliderItemsCount = 5),
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTopViewPager()
        setupGamesViewPager()
    }

    override fun updateHotGames(newPosition: Int) =
        binding.vpHotGames.setCurrentItem(newPosition, true)

    override fun setNewSliderItem(previousTab: Int, newTab: Int) {
        binding.tabLayout.apply {
            getTabAt(previousTab)?.unselectTab()
            getTabAt(newTab)?.selectTab()
        }
    }

    private fun setupTopViewPager() = binding.apply {
        vpHotGames.adapter = HotGamesAdapter(getFragments(), lifecycle, childFragmentManager)
        vpHotGames.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                homePresenter.pageChanged(position)
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