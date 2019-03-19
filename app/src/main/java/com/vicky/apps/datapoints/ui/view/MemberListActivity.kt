package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.AppConstants.COMPANY_DATA_BUNDLE
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter
import com.vicky.apps.datapoints.ui.adapter.MemberListAdapter
import com.vicky.apps.datapoints.ui.model.ResponseData
import com.vicky.apps.datapoints.ui.viewmodel.MemberListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_member_list.*
import javax.inject.Inject

class MemberListActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MemberListViewModel

    private lateinit var responseData: ResponseData

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MemberListAdapter

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
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        recyclerView = memberRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        recyclerView.isNestedScrollingEnabled = true
        adapter = MemberListAdapter(viewModel.getMemberData())
        recyclerView.adapter = adapter
    }

    private fun initializeValues() {
        viewModel = ViewModelProviders.of(this, factory).get(MemberListViewModel::class.java)


        viewModel.setMemberData(responseData.members)

        Picasso.get().load("https://name").placeholder(R.drawable.logo)
            .error(R.drawable.logo)
            .into(logoImage)

        companyNameText.text = responseData.company
        descriptionText.text = responseData.about
        websiteText.text = responseData.website

        sortAge .setOnClickListener { sortByAge() }

        sortName .setOnClickListener{ sortByName() }
    }

    private fun sortByName() {
        viewModel.sortByName()
        adapter.updatedata(viewModel.getMemberData())
    }

    private fun sortByAge() {
        viewModel.sortByAge()
        adapter.updatedata(viewModel.getMemberData())
    }


}
