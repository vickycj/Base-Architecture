package com.vicky.apps.datapoints.ui.view
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter

import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.app.SearchManager
import android.content.Context
import android.text.TextUtils
import android.view.MenuItem
import android.widget.SearchView

import com.vicky.apps.datapoints.ui.model.CompanyDetails


class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {



    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MainViewModel

    private var searchView: SearchView? = null
    private var searchMenuItem: MenuItem? = null
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


        adapter = DataAdapter( ArrayList()){ position:Int , com->
            clicked(position,com)
        }
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

        sortCompany.setOnClickListener { v -> sortAndUpdateData() }

        viewModel.getDataFromRemote()
    }

    private fun sortAndUpdateData() {
        viewModel.sortCompanyData()
        updateData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.vicky.apps.datapoints.R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchMenuItem = menu!!.findItem(com.vicky.apps.datapoints.R.id.search)
        searchView = searchMenuItem!!.actionView as SearchView

        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.isSubmitButtonEnabled = true
        searchView!!.setOnQueryTextListener(this)

        return true
    }


    private fun successCallback(){
        updateData()
    }

    private fun updateData(){
        adapter.updateData(viewModel.getCompanyDetails())
    }

    private fun updataDataWithCustomList(companyDetails: List<CompanyDetails>){
        adapter.updateData(companyDetails)
    }

    private fun failureCallback(){
        Toast.makeText(this,"API failed",Toast.LENGTH_LONG).show()
    }

     fun clicked(position: Int, companyDetail: CompanyDetails) {
        Toast.makeText(this,"Clicked position $position ${companyDetail.name}",Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (TextUtils.isEmpty(newText)) {
           updateData()
        }
        else {
            updataDataWithCustomList(viewModel.filterCompany(newText))
        }

        return true
    }
}
