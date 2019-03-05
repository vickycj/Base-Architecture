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


    private val dataFields : MutableList<DataFields> = ArrayList()

    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription():MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable


    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }

    fun getDataFields(): MutableList<DataFields> = dataFields

    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy ( onSuccess = {
            modifyResponseData(it)
        }, onError = {
            Log.d("valuessss",it.message)

        } ))


    }

    private fun modifyResponseData(responseData: ResponseData) {
      var groupedVal :  Map<String,List<Record>> =    responseData.result.records.groupBy {
                it.quarter.split("-")[0]
            }


        addDataTODataField(groupedVal)
    }

    private fun addDataTODataField(groupedVal:Map<String,List<Record>>) {
        for ((key, value) in groupedVal) {
            checkAndSumTheValue(key,value)
        }

        updateTheValuesToUI();

    }

    private fun updateTheValuesToUI() {
        response.postValue(true)
    }

    private fun checkAndSumTheValue( key:String, listCount: List<Record>){

        var total_volume = 0.0

        var lowestQuarter = ""
        var lowestVolume = 0.0

        listCount.forEach {
            total_volume += it.volume_of_mobile_data.toDouble()
            if(lowestQuarter.isNullOrEmpty()){
                lowestQuarter = it.quarter
                lowestVolume = it.volume_of_mobile_data.toDouble()
            }else{
                if(it.volume_of_mobile_data.toDouble() < lowestVolume){
                    lowestVolume = it.volume_of_mobile_data.toDouble()
                    lowestQuarter = it.quarter
                }
            }


        }



        val dataField = DataFields(key, String.format("%3f",total_volume), lowestQuarter, checkFirstQuarter(lowestQuarter))

        dataFields.add(dataField)
    }

    private fun checkFirstQuarter(check:String):Boolean = check.split("-")[1] != "Q1"




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