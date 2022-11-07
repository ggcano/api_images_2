package com.example.api_images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_images.services.Src
import com.example.api_images.client.Repo

class MainViewModel: ViewModel() {

    private val repo = Repo()
    private val mLiveData = MutableLiveData<Src>()

    fun photoData(): MutableLiveData<Src> {

        repo.getPhotoService().observeForever {
            mLiveData.value = it

        }
        return mLiveData
    }

}