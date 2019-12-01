package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class Clips(
    @SerializedName("full")
    var full: String,
    @SerializedName("320")
    var x320: String,
    @SerializedName("640")
    var x640: String
)