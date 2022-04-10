package com.example.ingame

import com.example.ingame.data.repository.GamesRepository
import com.example.ingame.ui.fragments.home.HomePresenter
import com.example.ingame.ui.fragments.home.HomeView
import com.example.ingame.ui.schedulers.TestingSchedulers
import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class HomePresenterTests {

    private lateinit var homePresenterDifferentDays: HomePresenter
    private lateinit var homePresenterEqualDays: HomePresenter

    @Mock
    private lateinit var gamesRepository: GamesRepository

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var homeView: HomeView

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        homePresenterDifferentDays = HomePresenter(
            schedulers = TestingSchedulers,
            gamesRepository = gamesRepository,
            router = router,
            lastKnownDate = "2015-10-05",
            today = "2018-10-05"
        )
        homePresenterEqualDays = HomePresenter(
            schedulers = TestingSchedulers,
            gamesRepository = gamesRepository,
            router = router,
            lastKnownDate = "2015-10-05",
            today = "2015-10-05"
        )
    }

    @Test
    fun testPresenterSetup_Success() {
        homePresenterDifferentDays.attachView(homeView)

        verify(homeView, times(1)).setupGamesViewPager()
        verify(homeView, times(1)).selectPageText(page = 0)
    }

    @Test
    fun testGetPlatforms_Success() {
        `when`(gamesRepository.getPlatformsList()).thenReturn(Single.just(listOf()))

        homePresenterDifferentDays.attachView(homeView)
        homePresenterDifferentDays.getPlatformsList()

        verify(homeView).setupPlatformsList(listOf())
    }

    @Test
    fun testGetPlatforms_Error() {
        `when`(gamesRepository.getPlatformsList()).thenReturn(Single.error(RuntimeException()))

        homePresenterDifferentDays.attachView(homeView)
        homePresenterDifferentDays.getPlatformsList()

        verify(homeView).showError()
    }

    @Test
    fun getSliderGames_DifferentDays_CallsApi() {
        `when`(gamesRepository.getUpdatedHotGames(
            anyInt(),
            anyString(),
            anyInt()
        )).thenReturn(Single.just(listOf()))

        homePresenterDifferentDays.getSliderGames()

        verify(gamesRepository).getUpdatedHotGames(
            page = anyInt(),
            updated = anyString(),
            pageSize = anyInt())
    }

    @Test
    fun getSliderGames_SameDay_CallsDb() {
        `when`(gamesRepository.getHotGames(
            anyInt(),
            anyString(),
            anyInt()
        )).thenReturn(Single.just(listOf()))

        homePresenterEqualDays.getSliderGames()

        verify(gamesRepository).getHotGames(
            page = anyInt(),
            updated = anyString(),
            pageSize = anyInt())
    }

    @Test
    fun gamePageSelected_PageIsNull_NoViewInteractions() {
        homePresenterDifferentDays.onGamesPageSelected(position = null)
        verifyNoInteractions(homeView)
    }

    @Test
    fun gamePageUnSelected_PageIsNull_NoViewInteractions() {
        homePresenterDifferentDays.onGamesPageUnselected(position = null)
        verifyNoInteractions(homeView)
    }

    @Test
    fun testBackNavigation_ReturnsTrue() {
        assertTrue(homePresenterDifferentDays.backPressed())
        verify(router, times(1)).exit()
    }
}