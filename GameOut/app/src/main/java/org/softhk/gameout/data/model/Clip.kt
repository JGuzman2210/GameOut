package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class Clip(
    @SerializedName("clip")
    var clip: String,
    @SerializedName("clips")
    var clips: Clips,
    @SerializedName("preview")
    var preview: String,
    @SerializedName("video")
    var video: String
)