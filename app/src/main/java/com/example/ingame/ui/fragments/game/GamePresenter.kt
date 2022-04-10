package com.example.ingame.ui.fragments.game

import com.example.ingame.data.network.model.game_detail.GameDetails
import com.example.ingame.data.repository.GamesRepository
import com.example.ingame.ui.schedulers.ISchedulers
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class GamePresenter @AssistedInject constructor(
    @Assisted(value = "gameId") private val gameId: Int,
    private val router: Router,
    private val gamesRepository: GamesRepository,
    private val ISchedulers: ISchedulers
) :
    MvpPresenter<GameView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.selectPageText(page = 0)
        loadGameData()
    }

    fun onCreateView() = viewState.initActionBar()

    fun onViewCreated() = viewState.setupListeners()

    private fun loadGameData() {
        if (gameId == -1) {
            onGetGameError()
            return
        }
        disposables += gamesRepository.getGameDetails(gameId)
            .observeOn(ISchedulers.main())
            .subscribeBy(
                onSuccess = (::onGetGameSuccess),
                onError = { onGetGameError() }
            )
    }

    private fun onGetGameSuccess(gameDetails: GameDetails) = viewState.apply {
        setGameData(gameDetails = gameDetails)
        initViewPager(gameDetails = gameDetails)
    }

    private fun onGetGameError() {
        println("Error")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onTabSelected(position: Int?) = position?.let(viewState::selectPageText)

    fun onTabUnselected(position: Int?) = position?.let(viewState::unselectPageText)

    fun onWebsiteClicked() = true

    fun onFavouritesClicked()= true

    fun onPlayedClicked()= true
}
