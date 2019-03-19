package com.vicky.apps.datapoints.data.remote


import com.vicky.apps.datapoints.ui.model.ResponseData
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("api/json/get/Vk-LhK44U")
    fun getDataFromService(): Single<List<ResponseData>>
}
