package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class Year(
    @SerializedName("count")
    var count: Int,
    @SerializedName("decade")
    var decade: Int,
    @SerializedName("filter")
    var filter: String,
    @SerializedName("from")
    var from: Int,
    @SerializedName("nofollow")
    var nofollow: Boolean,
    @SerializedName("to")
    var to: Int,
    @SerializedName("years")
    var years: List<YearX>
)