package com.example.ingame.data.db.helper

import com.example.ingame.data.db.GamesDataBase
import com.example.ingame.data.db.model.HotGame
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DBHelper @Inject constructor(
    private val db: GamesDataBase
) : IDBHelper {

    private val gamesDao = db.gamesDao()

    override fun getHotGames(): Single<List<Int>> = gamesDao.getHotGames()

    override fun getHotGameById(id: Int) = gamesDao.getHotGameById(id)

    override fun fetchHotGames(hotGames: List<HotGame>): Single<List<Int>> = gamesDao
        .insertHotGames(hotGames)
        .andThen(getHotGames())

    override fun clearHotGamesCache(): Completable = gamesDao.clearHotGames()
}