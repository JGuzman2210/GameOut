package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class ShortScreenshot(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: String
)