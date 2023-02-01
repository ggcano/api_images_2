package com.example.api_images.client

import com.example.api_images.servicesnew.Pexels
import retrofit2.Response

class Repo(private val retrofitService: ApiService?) {

    suspend fun getListPhotos(query: String):Response <Pexels>? {
        return retrofitService?.fetchListPhotos("563492ad6f917000010000016ee415c7e5144defbc009b1eae08ca1c",query)
    }



}