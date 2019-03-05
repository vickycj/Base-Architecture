package com.vicky.apps.datapoints.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel


class DataViewModelFactory(private val repository: Repository, private val schedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            return MainViewModel(repository, schedulerProvider) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName())
    }
}