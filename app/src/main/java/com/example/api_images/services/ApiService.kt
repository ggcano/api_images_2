package com.example.api_images.services


import com.example.api_images.dto.ApiDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("bpi/currentprice.json")
    fun getApi (): Call<ApiDto>

}