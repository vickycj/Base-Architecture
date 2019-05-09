package com.vicky.apps.datapoints.data.remote

import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getDataFromApi(): Single<List<Any>> = apiService.getDataFromService()

}