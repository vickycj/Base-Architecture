package com.vicky.apps.datapoints.ui.viewmodel

import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import io.reactivex.Single
import java.util.*


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
) {




    fun getDataFromRemote(): Single<Any> {
      return   repository.getDataFromApi(createRequestParams())
          .compose(schedulerProvider.getSchedulersForSingle())
    }


    private fun createRequestParams(): Map<String,String>{
        var requestparam: MutableMap<String,String> = HashMap()
        requestparam["resource_id"] = AppConstants.RESOURCE_ID
        requestparam["limit"] = AppConstants.LIMIT
        return  requestparam
    }
}