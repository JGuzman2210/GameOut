package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("years")
    var years: List<Year>
)