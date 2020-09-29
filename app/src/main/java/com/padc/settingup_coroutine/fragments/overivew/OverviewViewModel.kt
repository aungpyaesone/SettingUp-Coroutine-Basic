package com.padc.settingup_coroutine.fragments.overivew

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padc.settingup_coroutine.data.vos.MarsProperty
import com.padc.settingup_coroutine.network.MarsApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MarApiStatus {LOADING,ERROR,DONE}

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarApiStatus>()
    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties : LiveData<List<MarsProperty>>
    get() = _properties

    val status :LiveData<MarApiStatus>
    get() = _status

    init {
        getMarsRealEasteProperties()
    }

    private fun getMarsRealEasteProperties() {
       viewModelScope.launch {
           _status.value = MarApiStatus.LOADING
           try {

               _properties.value = MarsApi.retrofitService.getProperties()
               _status.value = MarApiStatus.DONE

           }
           catch (e: Exception){
               _status.value = MarApiStatus.ERROR
               _properties.value = arrayListOf()
           }
       }
    }
}