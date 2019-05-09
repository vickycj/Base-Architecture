package com.vicky.apps.datapoints.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R


class DataAdapter constructor() : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {


    }

    fun updateData(){

    }
    class DataViewHolder(v:View): RecyclerView.ViewHolder(v){
    }
}