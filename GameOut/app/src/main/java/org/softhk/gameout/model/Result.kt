package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("added")
    var added: Int,
    @SerializedName("added_by_status")
    var addedByStatus: AddedByStatus,
    @SerializedName("background_image")
    var backgroundImage: String,
    @SerializedName("clip")
    var clip: Clip,
    @SerializedName("dominant_color")
    var dominantColor: String,
    @SerializedName("genres")
    var genres: List<Genre>,
    @SerializedName("id")
    var id: Int,
    @SerializedName("metacritic")
    var metacritic: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("parent_platforms")
    var parentPlatforms: List<ParentPlatform>,
    @SerializedName("platforms")
    var platforms: List<PlatformX>,
    @SerializedName("playtime")
    var playtime: Int,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("rating_top")
    var ratingTop: Int,
    @SerializedName("ratings")
    var ratings: List<Rating>,
    @SerializedName("ratings_count")
    var ratingsCount: Int,
    @SerializedName("released")
    var released: String,
    @SerializedName("reviews_count")
    var reviewsCount: Int,
    @SerializedName("reviews_text_count")
    var reviewsTextCount: Int,
    @SerializedName("saturated_color")
    var saturatedColor: String,
    @SerializedName("short_screenshots")
    var shortScreenshots: List<ShortScreenshot>,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("stores")
    var stores: List<Store>,
    @SerializedName("suggestions_count")
    var suggestionsCount: Int,
    @SerializedName("tba")
    var tba: Boolean,
    @SerializedName("user_game")
    var userGame: Any?
)