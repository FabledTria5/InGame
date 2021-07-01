package com.example.ingame.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ingame.data.db.dao.GamesDao
import com.example.ingame.data.db.model.HotGame

@Database(entities = [HotGame::class], version = 1)
abstract class GamesDataBase : RoomDatabase() {
    abstract fun gamesDao() : GamesDao
}