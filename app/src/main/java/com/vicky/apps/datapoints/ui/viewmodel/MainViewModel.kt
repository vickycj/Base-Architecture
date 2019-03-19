package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.ResponseData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {




    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription():MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var responseData:List<ResponseData>


    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }



    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy ( onSuccess = {
            this.responseData = it
            Log.d("responseData",responseData.size.toString())
        }, onError = {
            Log.d("valuessss",it.message)
        } ))


    }

    private fun modifyResponseData() {

    }



    private fun updateTheValuesToUI() {
        response.postValue(true)
    }







    fun generateApiCall():Single<List<ResponseData>>{
        return repository.getDataFromApi()
            .compose(schedulerProvider.getSchedulersForSingle())
    }



}