package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("slug")
    var slug: String
)