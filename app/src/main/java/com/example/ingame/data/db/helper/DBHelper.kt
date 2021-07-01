package com.example.ingame.data.db.helper

import com.example.ingame.data.db.GamesDataBase
import com.example.ingame.data.db.model.HotGame
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DBHelper @Inject constructor(
    private val db: GamesDataBase
) : IDBHelper {

    override fun getHotGames(): Single<List<Int>> = db.gamesDao().getHotGames()

    override fun getHotGameById(id: Int) = db.gamesDao().getHotGameById(id)

    override fun fetchHotGames(hotGames: List<HotGame>): Single<List<Int>> = db.gamesDao()
        .insertHotGames(hotGames)
        .andThen(getHotGames())
}