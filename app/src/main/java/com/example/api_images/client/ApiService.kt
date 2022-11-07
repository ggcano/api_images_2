package com.example.api_images.client

import com.example.api_images.services.Src
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    //https://api.pexels.com/v1/search?query=mature&per_page=1
    @GET("search?query=mature&per_page=1")
    fun fetchPosts(@Header("Authorization") token: String): Call<Src>

}