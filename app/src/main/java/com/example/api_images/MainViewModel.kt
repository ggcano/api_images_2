package com.example.api_images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_images.services.Src
import com.example.api_images.client.Repo
import com.example.api_images.servicesnew.Photo2
import kotlinx.coroutines.*
import retrofit2.Response

class MainViewModel constructor(private val repo: Repo) : ViewModel() {

    private var job: Job? = null
    var responseListMLD: MutableLiveData<Response<Src>?> = MutableLiveData()

    private val _photos = MutableLiveData<List<Photo2>>(emptyList())
    val photos:LiveData<List<Photo2>> get()= _photos


    fun getPhotoAndPhotographer(token:String, path: String) {
        viewModelScope.launch {
            val response = repo.getFetch(token,path)
            responseListMLD.value = response
        }
    }

    fun getAllPhotos(token:String, path: String) {
        viewModelScope.launch {
         val response = repo.getListPhotos(token, path)
        _photos.value = response!!.body()!!.photo2s
        }
    }

    fun jobCancel(){
        super.onCleared()
        job?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}