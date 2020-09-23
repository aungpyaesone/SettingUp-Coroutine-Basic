package com.padc.settingup_coroutine.network

import com.padc.settingup_coroutine.data.vos.MarsProperty
import retrofit2.http.GET

interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties() : List<MarsProperty>
}