package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class ParentPlatform(
    @SerializedName("platform")
    var platform: Platform
)