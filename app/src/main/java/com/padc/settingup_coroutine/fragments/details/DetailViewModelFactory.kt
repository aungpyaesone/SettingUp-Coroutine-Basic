package com.padc.settingup_coroutine.fragments.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.padc.settingup_coroutine.data.vos.MarsProperty
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val marsProperty: MarsProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(marsProperty,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}