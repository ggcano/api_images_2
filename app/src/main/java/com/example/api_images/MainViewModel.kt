package com.example.api_images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_images.services.Src
import com.example.api_images.client.Repo
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel constructor(private val repo: Repo) : ViewModel() {

/*    private val repo = Repo()
    private val mLiveData = MutableLiveData<Src>()

    fun photoData(): MutableLiveData<Src> {

        repo.getPhotoService().observeForever {
            mLiveData.value = it

        }
        return mLiveData
    }

    fun stringSearch (): MutableLiveData<String> {
        repo.getPhotoService()
    }*/

    var job: Job? = null
    var responseListMLD: MutableLiveData<Response<Src>?> = MutableLiveData()

    fun getCustomPost(token:String) {
        viewModelScope.launch {
            val response = repo.getFetch(token)
            responseListMLD.value = response
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}