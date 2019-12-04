package org.softhk.gameout.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
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
):Parcelable