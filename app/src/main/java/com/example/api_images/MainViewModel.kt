package com.example.api_images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_images.dto.ApiDto
import com.example.api_images.services.RepoBitCoin

class MainViewModel: ViewModel() {

    private val repo = RepoBitCoin()
    private val mLiveData = MutableLiveData<ApiDto>()

    fun coinData(): MutableLiveData<ApiDto> {

        repo.getBitCoinFromApi().observeForever {
            mLiveData.value = it

        }
        return mLiveData
    }

}