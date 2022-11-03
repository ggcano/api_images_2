package com.example.api_images.dto

import com.google.gson.annotations.SerializedName

data class BpiDto(

    @SerializedName("USD")
    val usd: CoinDto,

    @SerializedName("GBP")
    val gpb: CoinDto,

    @SerializedName("EUR")
    val eur: CoinDto
)