package com.padc.settingup_coroutine.fragments.overivew

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padc.settingup_coroutine.data.vos.MarsProperty
import com.padc.settingup_coroutine.network.MarsApi
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }
class OverviewViewModel : ViewModel(){

    private val _status = MutableLiveData<MarsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MarsApiStatus>
        get() = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _properties = MutableLiveData<List<MarsProperty>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Gets Mars real estate property information from the Mars API Retrofit service and updates the
     * [MarsProperty] [List] [LiveData]. The Retrofit service returns a coroutine Deferred, which we
     * await to get the result of the transaction.
     */
    private fun getMarsRealEstateProperties() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _properties.value = MarsApi.retrofitService.getProperties()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
}