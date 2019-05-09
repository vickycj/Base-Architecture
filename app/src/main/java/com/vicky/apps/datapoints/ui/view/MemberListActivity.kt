package com.vicky.apps.datapoints.ui.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
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
import com.vicky.apps.datapoints.ui.model.Member
import com.vicky.apps.datapoints.ui.model.ResponseData
import com.vicky.apps.datapoints.ui.viewmodel.MemberListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_member_list.*
import javax.inject.Inject

class MemberListActivity : BaseActivity(), SearchView.OnQueryTextListener {


    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MemberListViewModel

    private lateinit var responseData: ResponseData

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: MemberListAdapter

    private var searchView: SearchView? = null
    private var searchMenuItem: MenuItem? = null

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
        adapter = MemberListAdapter(ArrayList())
        recyclerView.adapter = adapter


        adapter.updatedata(viewModel.getMemberData())
    }

    private fun initializeValues() {
        viewModel = ViewModelProviders.of(this, factory).get(MemberListViewModel::class.java)


        viewModel.setMemberData(responseData.members)

        Picasso.get().load(responseData.logo).placeholder(R.drawable.logo)
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
       updateData()
    }

    private fun updateData(){
        adapter.updatedata(viewModel.getMemberData())
    }

    private fun sortByAge() {
        viewModel.sortByAge()
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (TextUtils.isEmpty(newText)) {
            updateData()
        }
        else {
            updataDataWithCustomList(viewModel.filterMember(newText))
        }

        return true
    }

    private fun updataDataWithCustomList(member: List<Member>) {
        adapter.updatedata(member)
    }

}
