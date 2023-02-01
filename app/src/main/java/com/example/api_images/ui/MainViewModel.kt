package com.example.api_images.ui

import androidx.lifecycle.*

import com.example.api_images.client.Repo

import com.example.api_images.servicesnew.Pexels
import com.example.api_images.servicesnew.Photo2
import kotlinx.coroutines.*
import retrofit2.Response

class MainViewModel constructor(private val repo: Repo) : ViewModel() {

    private var job: Job? = null


    private val _photos = MutableLiveData<List<Photo2>>()
    private val _photos33 = MutableLiveData<Photo2>()
    val photos:LiveData<List<Photo2>> get()= _photos
    val movieList = MutableLiveData<String>()



    fun getAllPhotos(path: String) {
        viewModelScope.launch {
         val response = repo.getListPhotos( path)
        _photos.value = response?.body()?.photo2s
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

    fun showFilm( path: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getListPhotos( path)
            withContext(Dispatchers.Main) {
                if (response != null) {
                    if (response.isSuccessful) {
                        movieList.postValue(response.body()?.perPage.toString())

                    } else {
                        println("Error en showFilm")
                    }
                }
            }
        }

    }

}