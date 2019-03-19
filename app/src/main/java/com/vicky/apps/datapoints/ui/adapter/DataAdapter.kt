package com.vicky.apps.datapoints.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.CompanyDetails

class DataAdapter(var companyDetails: List<CompanyDetails>,val listenerAdapter: RecyclerViewClickListenerAdapter) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v,listenerAdapter)
    }

    override fun getItemCount(): Int = companyDetails.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.companyName.text = companyDetails[position].name

       // Picasso.get().load(companyDetails[position].logo).into(holder.logoImage)
    }

    fun updateData(companyDetails: List<CompanyDetails>){
        this.companyDetails = companyDetails
        notifyDataSetChanged()
    }
    class DataViewHolder(v:View,val listenerAdapter: RecyclerViewClickListenerAdapter): RecyclerView.ViewHolder(v),View.OnClickListener{

        val logoImage:ImageView = v.findViewById(R.id.companyLogo)
        val companyName:TextView = v.findViewById(R.id.companyName)

        init {
            logoImage.setOnClickListener { this }
        }
        override fun onClick(v: View?) {

        }

    }
}