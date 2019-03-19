package com.vicky.apps.datapoints.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.viewmodel.MemberListViewModel
import javax.inject.Inject

class MemberListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MemberListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_list)

        initializeValues()
    }

    private fun initializeValues() {
        viewModel = ViewModelProviders.of(this, factory).get(MemberListViewModel::class.java)
    }
}
