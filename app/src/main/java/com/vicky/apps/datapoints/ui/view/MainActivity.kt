package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainActivityViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
