package org.softhk.gameout.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("description")
    var description: String,
    @SerializedName("next")
    var next: String,
    @SerializedName("nofollow")
    var nofollow: Boolean,
    @SerializedName("nofollow_collections")
    var nofollowCollections: List<String>,
    @SerializedName("noindex")
    var noindex: Boolean,
    @SerializedName("previous")
    var previous: String?,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("seo_description")
    var seoDescription: String,
    @SerializedName("seo_h1")
    var seoH1: String,
    @SerializedName("seo_keywords")
    var seoKeywords: String,
    @SerializedName("seo_title")
    var seoTitle: String
):Parcelable