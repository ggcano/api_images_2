package com.example.api_images.client

import com.example.api_images.services.Src

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {

    @GET("search?query=nature&per_page=1")
    suspend fun fetchPhotos(
        @Header("Authorization") token: String,
        @Query("query") search: String
    ): Response<Src>

    companion object {
        var retrofitService: ApiService? = null
        fun getInstance() : ApiService? {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.pexels.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService
        }

    }


}