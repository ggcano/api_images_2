package com.example.api_images.client

import com.example.api_images.servicesnew.Pexels
import com.example.api_images.servicesnew.Photo2
import retrofit2.Call

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {


    //https://api.pexels.com/v1/search/?page=2&per_page=15&query=china"
    @GET("search/?page=1&per_page=150&query=china")
    suspend fun fetchListPhotos(
        @Header("Authorization") token: String,
        @Query("query") search: String
    ): Response<Pexels>


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