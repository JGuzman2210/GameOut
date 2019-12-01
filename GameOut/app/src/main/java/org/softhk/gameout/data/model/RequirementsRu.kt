package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class RequirementsRu(
    @SerializedName("minimum")
    var minimum: String,
    @SerializedName("recommended")
    var recommended: String
)