package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("count")
    var count: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("percent")
    var percent: Double,
    @SerializedName("title")
    var title: String
)