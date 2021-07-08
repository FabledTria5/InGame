package com.example.ingame.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ingame.data.db.dao.GamesDao
import com.example.ingame.data.db.model.HotGame
import com.example.ingame.data.db.model.Platform

@Database(entities = [HotGame::class, Platform::class], version = 4)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun gamesDao() : GamesDao
}