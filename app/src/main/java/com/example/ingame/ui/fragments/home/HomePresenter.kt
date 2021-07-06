package com.example.ingame.ui.fragments.home

import com.example.ingame.data.repository.IGamesRepository
import com.example.ingame.ui.schedulers.Schedulers
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Named

class HomePresenter @AssistedInject constructor(
    @Named(value = "today") private val today: String,
    @Named(value = "lastKnownDate") private val lastKnownDate: String,
    private val schedulers: Schedulers,
    private val gamesRepository: IGamesRepository,
    private val router: Router,
) :
    MvpPresenter<HomeView>() {

    private val disposables = CompositeDisposable()

    var wasDragging: Boolean = false
    var platformsListPosition = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupGamesViewPager()
        viewState.selectPageText(0)
        getSliderGames()
        getPlatformsList()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun getSliderGames() {
        if (today == lastKnownDate)
            disposables += getCachedGames()
                .observeOn(schedulers.main())
                .subscribeBy(
                    onSuccess = (::onGetSliderGamesSuccess),
                    onError = { onGetSliderGamesError() }
                )
        else {
            disposables += getNewListOfGames()
                .observeOn(schedulers.main())
                .subscribeBy(
                    onSuccess = (::onGetSliderGamesSuccess),
                    onError = { onGetSliderGamesError() }
                )
            viewState.updateDate(today)
        }
    }

    private fun getPlatformsList() {
        disposables += gamesRepository.getPlatformsList()
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetPlatformsSuccess)
            )
    }

    fun onPlatformSelected(itemPosition: Int, platformName: String) {
        platformsListPosition = itemPosition

        disposables += gamesRepository.getPlatformByName(platformName)
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetPlatformIdSuccess)
            )
    }

    private fun getCachedGames() = gamesRepository.getHotGames(
        page = 1,
        updated = today,
        pageSize = 5
    )

    private fun getNewListOfGames() = gamesRepository.getUpdatedHotGames(
        page = 1,
        updated = today,
        pageSize = 5
    )

    private fun onGetPlatformsSuccess(platforms: List<String>) {
        viewState.setupPlatformsList(platforms)
    }

    private fun onGetSliderGamesSuccess(hotGamesIds: List<Int>) = viewState.setupSlider(hotGamesIds)

    private fun onGetPlatformIdSuccess(platformId: Int) = viewState.setCurrentPlatform(platformId)

    private fun onGetSliderGamesError() {
        viewState.showError()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onGamesPageSelected(position: Int?) = position?.let(viewState::selectPageText)

    fun onGamesPageUnselected(position: Int?) = position?.let(viewState::unselectPageText)

}