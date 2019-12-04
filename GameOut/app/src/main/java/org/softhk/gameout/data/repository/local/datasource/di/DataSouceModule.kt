package org.softhk.gameout.data.repository.local.datasource.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.softhk.gameout.data.repository.local.GameFavoriteDAO
import org.softhk.gameout.data.repository.local.datasource.GameFavoriteDS
import javax.inject.Singleton

@Module
class DataSouceModule {


    @Singleton
    @Provides
    fun providerGameFavoriteDS(context:Context):GameFavoriteDS =
        Room.databaseBuilder(context,GameFavoriteDS::class.java,"DbGameFavorite.db")
            .build()

    @Singleton
    @Provides
    fun providerGameFavoriteDAO(gameFavoriteDS: GameFavoriteDS):GameFavoriteDAO =
        gameFavoriteDS.gameFavoriteDAO()
}