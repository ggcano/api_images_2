package com.example.api_images.dto

import com.google.gson.annotations.SerializedName

data class ApiDto (

    @SerializedName("disclaimer")
    val disclaimer: String,

    @SerializedName("chartName")
    val chartName: String,

    @SerializedName("bpi")
    val coinBpi: BpiDto
)