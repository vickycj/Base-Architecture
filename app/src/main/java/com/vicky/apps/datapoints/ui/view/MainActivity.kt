package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter
import com.vicky.apps.datapoints.ui.adapter.RecyclerViewClickListenerAdapter
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject




class MainActivity : BaseActivity(), RecyclerViewClickListenerAdapter {


    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MainViewModel


    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)


        inilializingRecyclerView()
        initializeValues()

    }

    private fun inilializingRecyclerView() {
        recyclerView = data_recycler
        recyclerView.layoutManager = GridLayoutManager(this, 3)


        adapter = DataAdapter( ArrayList(),this)
        recyclerView.adapter = adapter
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

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
        adapter.updateData(viewModel.getCompanyDetails())
    }

    private fun failureCallback(){
        Toast.makeText(this,"API failed",Toast.LENGTH_LONG).show()
    }

    override fun clicked(position: Int) {
        Toast.makeText(this,"Clicked position $position",Toast.LENGTH_LONG).show()
    }


}
