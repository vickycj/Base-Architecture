package com.vicky.apps.datapoints.data.remote


import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {
    @GET("api/action/datastore_search")
    fun getDataFromService(): Single<Any>
}
