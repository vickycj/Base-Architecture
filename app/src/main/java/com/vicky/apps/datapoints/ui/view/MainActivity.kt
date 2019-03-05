package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainActivity : BaseActivity(),LifecycleOwner {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initializeValues()

    }

    private fun initializeValues() {
        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(this, Observer {
            if(it){
                successCallback()
            }else{
                failureCallback()
            }
        })


        viewModel.getDataFromRemote()
    }


    private fun successCallback(){

    }

    private fun failureCallback(){

    }


}
