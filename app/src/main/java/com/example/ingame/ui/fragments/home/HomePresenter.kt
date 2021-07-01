package com.example.ingame.ui.fragments.home

import com.example.ingame.data.repository.GamesRepository
import com.example.ingame.ui.schedulers.Schedulers
import com.example.ingame.utils.DateFormatter
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class HomePresenter @AssistedInject constructor(
    private val schedulers: Schedulers,
    private val gamesRepository: GamesRepository,
    private val router: Router,
    private val dateFormatter: DateFormatter
) :
    MvpPresenter<HomeView>() {

    private val disposables = CompositeDisposable()

    var wasDragging: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupGamesViewPager()
        viewState.selectPageText(0)
        getSliderGames()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun getSliderGames() {
        disposables += gamesRepository.getListOfGames(
            page = 1,
            updated = dateFormatter.getToday(),
            pageSize = 5
        )
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetSliderGamesSuccess),
                onError = (::onGetSliderGamesError)
            )
    }

    private fun onGetSliderGamesSuccess(hotGamesIds: List<Int>) = viewState.setupSlider(hotGamesIds)

    private fun onGetSliderGamesError(t: Throwable) {
        viewState.showError()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onGamesPageSelected(position: Int?) = position?.let(viewState::selectPageText)

    fun onGamesPageUnselected(position: Int?) = position?.let(viewState::unselectPageText)

}