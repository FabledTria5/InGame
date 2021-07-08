package com.example.ingame.ui.fragments.popular

import com.example.ingame.data.network.model.games_list.GamesList
import com.example.ingame.data.network.model.games_list.Result
import com.example.ingame.data.repository.IGamesRepository
import com.example.ingame.ui.adapters.recyclerviews.home.IHomeGamesPresenter
import com.example.ingame.ui.base.GamesLoaderView
import com.example.ingame.ui.adapters.recyclerviews.home.IHomeGamesViewHolder
import com.example.ingame.ui.navigation.IScreens
import com.example.ingame.ui.schedulers.Schedulers
import com.example.ingame.utils.DateFormatter
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class PopularPresenter @AssistedInject constructor(
    private val schedulers: Schedulers,
    private val gamesRepository: IGamesRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<GamesLoaderView>() {

    private val disposables = CompositeDisposable()

    private var selectedPlatform = -1

    inner class HomeGamesPresenter : IHomeGamesPresenter {

        val gamesList = mutableListOf<Result>()

        override var itemClickListener: ((position: Int) -> Unit)? = null

        override fun bindView(view: IHomeGamesViewHolder, position: Int) =
            view.bindImage(gamesList[position].background_image, position)

        override fun getCount() = gamesList.count()

        override fun imageReady(position: Int) {
            if (position == gamesList.count()) {
                viewState.fillList()
            }
        }
    }

    val homeGamesPresenter = HomeGamesPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupRecycler()

        homeGamesPresenter.itemClickListener = { itemPosition ->
            router.navigateTo(screen = screens.games(homeGamesPresenter.gamesList[itemPosition].id))
        }
    }

    fun platformSelected(platformId: Int) {
        if (platformId == selectedPlatform) return
        disposables += gamesRepository.getGamesByPlatform(
            platform = platformId,
            dates = DateFormatter.getLastMonthsInApiForm(count = 3)
        )
            .observeOn(schedulers.main())
            .subscribeBy(
                onSuccess = (::onGetGamesSuccess)
            )
        selectedPlatform = platformId
    }

    private fun onGetGamesSuccess(gamesList: GamesList) {
        if (gamesList.results.isNullOrEmpty()) {
            viewState.showEmptyMessage()
            return
        }
        homeGamesPresenter.gamesList.clear()
        viewState.clearList()
        homeGamesPresenter.gamesList.addAll(gamesList.results)
        viewState.showList()
    }
}