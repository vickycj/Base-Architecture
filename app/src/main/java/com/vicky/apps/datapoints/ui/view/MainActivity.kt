package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import android.util.Log
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getDataFromRemote()
    }

    private fun getDataFromRemote() {



        compositeDisposable.add(viewModel.getDataFromRemote().subscribeBy ( onSuccess = {
            Log.d("valuessss",it.toString())
        }, onError = {
            Log.d("valuessss",it.message)

        } ));

    }
}
