package org.softhk.gameout.data.model


import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("id")
    var id: Int,
    @SerializedName("store")
    var store: StoreX,
    @SerializedName("url_en")
    var urlEn: String,
    @SerializedName("url_ru")
    var urlRu: Any?
)