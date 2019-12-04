package org.softhk.gameout.data.repository.local.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import org.softhk.gameout.data.entity.GameFavorite
import org.softhk.gameout.data.repository.local.GameFavoriteDAO

@Database(entities = arrayOf(GameFavorite::class),version = 1)
abstract class  GameFavoriteDS : RoomDatabase(){

    abstract fun gameFavoriteDAO():GameFavoriteDAO

}