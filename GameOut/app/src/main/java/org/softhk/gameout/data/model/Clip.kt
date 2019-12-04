package org.softhk.gameout.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clip(
    @SerializedName("clip")
    var clip: String,
    @SerializedName("clips")
    var clips: Clips,
    @SerializedName("preview")
    var preview: String,
    @SerializedName("video")
    var video: String
):Parcelable