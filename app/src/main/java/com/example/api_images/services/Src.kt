package com.example.api_images.services


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Src(
    @SerializedName("next_page")
    var nextPage: String,
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("photos")
    var photos: List<Photo>,
    @SerializedName("total_results")
    var totalResults: Int
):Serializable