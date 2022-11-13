package com.example.api_images.servicesnew


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pexels(
    @SerializedName("next_page")
    var nextPage: String,
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("photos")
    var photo2s: List<Photo2>,
    @SerializedName("total_results")
    var totalResults: Int
):Serializable