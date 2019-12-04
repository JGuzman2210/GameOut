package org.softhk.gameout.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
    @SerializedName("count")
    var count: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("percent")
    var percent: Double,
    @SerializedName("title")
    var title: String
):Parcelable