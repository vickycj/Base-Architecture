package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.DataFields
import com.vicky.apps.datapoints.ui.model.Record
import com.vicky.apps.datapoints.ui.model.ResponseData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import kotlin.collections.ArrayList


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {


    private val dataFields : List<DataFields> = ArrayList()

    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription():MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable


    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }

    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy ( onSuccess = {
            modifyResponseData(it)
        }, onError = {
            Log.d("valuessss",it.message)

        } ))


    }

    private fun modifyResponseData(responseData: ResponseData) {
      var value :  Map<String,List<Record>> =    responseData.result.records.groupBy {
                it.quarter.split("-")[0]
            }
        
    }



    private fun generateApiCall():Single<ResponseData>{
        return repository.getDataFromApi(createRequestParams())
            .compose(schedulerProvider.getSchedulersForSingle())
    }


    private fun createRequestParams(): Map<String,String>{
        var requestparam: MutableMap<String,String> = HashMap()
        requestparam["resource_id"] = AppConstants.RESOURCE_ID
        requestparam["limit"] = AppConstants.LIMIT
        return  requestparam
    }
}