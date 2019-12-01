package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class PlatformXX(
    @SerializedName("games_count")
    var gamesCount: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: Any?,
    @SerializedName("image_background")
    var imageBackground: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("year_end")
    var yearEnd: Any?,
    @SerializedName("year_start")
    var yearStart: Any?
)