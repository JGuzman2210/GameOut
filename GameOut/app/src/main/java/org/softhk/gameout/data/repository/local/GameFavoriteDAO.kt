package org.softhk.gameout.data.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.softhk.gameout.data.entity.GameFavorite

@Dao
interface GameFavoriteDAO {

    @Insert
    fun insert(gameFavorite: GameFavorite)


    @Query("SELECT * FROM GameFavorite")
    fun getAllGameFavorite():List<GameFavorite>

    @Delete
    fun deleteGameFavoriteById(gameFavorite: GameFavorite)
}