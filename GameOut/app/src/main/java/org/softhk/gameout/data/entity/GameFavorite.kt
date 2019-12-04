package org.softhk.gameout.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GameFavorite")
data class GameFavorite constructor(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "url_image")
    val urlImage: String,

    @ColumnInfo(name = "released")
    val released: String,

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean


)
