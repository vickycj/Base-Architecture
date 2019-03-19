package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.CompanyDetails
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

    private var companyDetails:MutableList<CompanyDetails> = ArrayList()
    private var ascendingVal:Boolean = false

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }



    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy ( onSuccess = {
            this.responseData = it
            Log.d("responseData",responseData.size.toString())
            updateTheValuesToUI()
        }, onError = {
            Log.d("valuessss",it.message)
        } ))


    }


    fun getCompanyDetails():List<CompanyDetails>{
        return companyDetails
    }

    private fun updateTheValuesToUI() {
        responseData.forEach {
            companyDetails.add(CompanyDetails(it._id, it.logo,it.company))
        }
        response.postValue(true)
    }


    fun generateApiCall():Single<List<ResponseData>>{
        return repository.getDataFromApi()
            .compose(schedulerProvider.getSchedulersForSingle())
    }

    fun sortCompanyData(){
         if(!ascendingVal){
            companyDetails = ArrayList(companyDetails).sortedBy {
                it.name
            }.toMutableList()
           ascendingVal= true
        }else{
            companyDetails = ArrayList(companyDetails).sortedByDescending {
                it.name
            }.toMutableList()
             ascendingVal=  false
        }
    }



}