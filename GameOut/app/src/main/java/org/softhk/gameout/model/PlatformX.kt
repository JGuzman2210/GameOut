package org.softhk.gameout.model


import com.google.gson.annotations.SerializedName

data class PlatformX(
    @SerializedName("platform")
    var platform: PlatformXX,
    @SerializedName("released_at")
    var releasedAt: String,
    @SerializedName("requirements_en")
    var requirementsEn: Any?,
    @SerializedName("requirements_ru")
    var requirementsRu: RequirementsRu?
)