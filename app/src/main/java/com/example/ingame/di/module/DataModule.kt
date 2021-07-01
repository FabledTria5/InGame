package com.example.ingame.di.module

import android.content.Context
import androidx.room.Room
import com.example.ingame.data.db.GamesDataBase
import com.example.ingame.data.db.helper.DBHelper
import com.example.ingame.data.db.helper.IDBHelper
import com.example.ingame.data.network.api.ApiHelper
import com.example.ingame.data.repository.GamesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideGamesDataBase(context: Context): GamesDataBase = Room
        .databaseBuilder(context, GamesDataBase::class.java, "games_database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDBHelper(db: GamesDataBase): IDBHelper = DBHelper(db)

    @Singleton
    @Provides
    fun provideGamesRepository(
        apiHelper: ApiHelper,
        dbHelper: IDBHelper
    ): GamesRepository = GamesRepository(apiHelper, dbHelper)

}