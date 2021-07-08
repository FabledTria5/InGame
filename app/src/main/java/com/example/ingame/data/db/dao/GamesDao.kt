package com.example.ingame.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.db.model.Platform
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotGames(hotGame: HotGame): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotGames(hotGame: List<HotGame>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlatforms(platforms: List<Platform>): Completable

    @Query(value = "SELECT gameId FROM hot_games")
    fun getHotGames(): Single<List<Int>>

    @Query(value = "SELECT * FROM hot_games WHERE gameId = :gameId")
    fun getHotGameById(gameId: Int): Single<HotGame>

    @Query(value = "SELECT platformName FROM platforms ORDER BY platformId DESC")
    fun getPlatformsNames(): Single<List<String>>

    @Query(value = "SELECT platformId FROM platforms WHERE platformName = :platformName")
    fun getPlatformByName(platformName: String): Single<Int>

    @Query(value = "DELETE FROM hot_games")
    fun clearHotGames(): Completable

}
