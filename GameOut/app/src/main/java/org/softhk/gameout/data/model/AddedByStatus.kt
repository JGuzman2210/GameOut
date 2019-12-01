package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class AddedByStatus(
    @SerializedName("beaten")
    var beaten: Int,
    @SerializedName("dropped")
    var dropped: Int,
    @SerializedName("owned")
    var owned: Int,
    @SerializedName("playing")
    var playing: Int,
    @SerializedName("toplay")
    var toplay: Int,
    @SerializedName("yet")
    var yet: Int
)