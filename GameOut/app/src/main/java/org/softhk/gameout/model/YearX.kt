package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class YearX(
    @SerializedName("count")
    var count: Int,
    @SerializedName("nofollow")
    var nofollow: Boolean,
    @SerializedName("year")
    var year: Int
)