package com.vicky.apps.datapoints.ui.adapter

import com.vicky.apps.datapoints.ui.model.CompanyDetails

interface RecyclerViewClickListenerAdapter {
    fun clicked(position:Int, companyDetail:CompanyDetails)
}