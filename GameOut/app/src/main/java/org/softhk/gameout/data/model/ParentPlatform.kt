package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    var platform: Platform
)