package com.example.ingame.ui.fragments.home

import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.repository.RetrofitRepositoryImpl
import com.example.ingame.ui.fragments.hot_game.HotGameFragment
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
    private val retrofitRepositoryImpl: RetrofitRepositoryImpl,
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
        disposables += retrofitRepositoryImpl.getListOfGames(
            page = 1,
            updated = dateFormatter.getToday(),
            pageSize = 5
        )
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetSliderGamesSuccess),
                onError = { onGetSliderGamesError() }
            )
    }

    private fun onGetSliderGamesSuccess(gamesList: GamesList) {
        val arrayOfSliderFragments = arrayListOf<HotGameFragment>()
        gamesList.results.forEach { result ->
            arrayOfSliderFragments.add(
                HotGameFragment.newInstance(result) as HotGameFragment
            )
        }
        viewState.setupSlider(arrayOfSliderFragments)
    }

    private fun onGetSliderGamesError() = viewState.showError()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onGamesPageSelected(position: Int?) = position?.let(viewState::selectPageText)

    fun onGamesPageUnselected(position: Int?) = position?.let(viewState::unselectPageText)

}