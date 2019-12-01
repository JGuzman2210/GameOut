package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class StoreX(
    @SerializedName("domain")
    var domain: String,
    @SerializedName("games_count")
    var gamesCount: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image_background")
    var imageBackground: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("slug")
    var slug: String
)