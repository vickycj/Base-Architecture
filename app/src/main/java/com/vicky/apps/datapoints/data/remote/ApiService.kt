package com.vicky.apps.datapoints.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import java.util.*

interface ApiService {
    @GET(".")
    fun getDataFromService(): Single<Objects>
}
