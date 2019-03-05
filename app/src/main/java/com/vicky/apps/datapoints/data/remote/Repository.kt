package com.vicky.apps.datapoints.data.remote

import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getDataFromApi(params: Map<String,String>): Single<Objects> = apiService.getDataFromService(params)

}