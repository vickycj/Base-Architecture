package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.AppConstants.COMPANY_DATA_BUNDLE
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.model.ResponseData
import com.vicky.apps.datapoints.ui.viewmodel.MemberListViewModel
import kotlinx.android.synthetic.main.activity_member_list.*
import javax.inject.Inject

class MemberListActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MemberListViewModel

    private lateinit var responseData: ResponseData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_list)


        if(intent != null){
            val b =intent.getBundleExtra(COMPANY_DATA_BUNDLE)
           val data = b.getParcelable<ResponseData>(AppConstants.COMPANY_DATA)
            if(data != null){
                responseData =data
            }
        }

        initializeValues()
    }

    private fun initializeValues() {
        viewModel = ViewModelProviders.of(this, factory).get(MemberListViewModel::class.java)


        Picasso.get().load("https://name").placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .into(logoImage)

        companyNameText.text = responseData.company
        descriptionText.text = responseData.about
        websiteText.text = responseData.website
    }
}
