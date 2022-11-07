package com.example.api_images.services


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("alt")
    var alt: String,
    @SerializedName("avg_color")
    var avgColor: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("liked")
    var liked: Boolean,
    @SerializedName("photographer")
    var photographer: String,
    @SerializedName("photographer_id")
    var photographerId: Int,
    @SerializedName("photographer_url")
    var photographerUrl: String,
    @SerializedName("src")
    var src: SrcX,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Int
):Serializable