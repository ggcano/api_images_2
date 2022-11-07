package com.example.api_images.client

import androidx.lifecycle.MutableLiveData
import com.example.api_images.services.Src
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {
    private val restClient: RestClient = RestClient()

    fun getPhotoService(): MutableLiveData<Src> {
        val mutableBitCoinData: MutableLiveData<Src> = MutableLiveData()
        val call = restClient.getService().fetchPosts("563492ad6f917000010000016ee415c7e5144defbc009b1eae08ca1c")

        call.enqueue(object : Callback<Src> {
            override fun onResponse(call: Call<Src>, response: Response<Src>) {
                mutableBitCoinData.value = response.body()
            }

            override fun onFailure(call: Call<Src>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return mutableBitCoinData
    }

}