package com.example.api_images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_images.services.Src
import com.example.api_images.client.Repo
import com.example.api_images.servicesnew.Photo2
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.http.Path

class MainViewModel constructor(private val repo: Repo) : ViewModel() {

    private var job: Job? = null
    var responseListMLD: MutableLiveData<Response<Src>?> = MutableLiveData()
    var responseListPhotos:  MutableLiveData<List<Photo2>> = MutableLiveData()

    fun getCustomPost(token:String,path: String) {
        viewModelScope.launch {
            val response = repo.getFetch(token,path)
            responseListMLD.value = response
        }
    }

    fun getAllMovies(token:String,path: String) {

        viewModelScope.launch {
            val response = repo.getListPhotos(token, path)
            responseListPhotos.value = response?.body()?.photo2s

        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}