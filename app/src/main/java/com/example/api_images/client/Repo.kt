package com.example.api_images.client

import com.example.api_images.services.Src
import com.example.api_images.servicesnew.Pexels
import com.example.api_images.servicesnew.Photo2
import retrofit2.Call
import retrofit2.Response

class Repo(private val retrofitService: ApiService?) {

    suspend fun getFetch(token:String,query: String): Response<Src>? {
        return retrofitService?.fetchPhotos(token,query)
    }

    suspend fun getListPhotos(token:String,query: String):Response <Pexels>? {
        return retrofitService?.fetchListPhotos(token,query)
    }



}