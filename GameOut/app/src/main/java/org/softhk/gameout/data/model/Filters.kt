package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("years")
    var years: List<Year>
)