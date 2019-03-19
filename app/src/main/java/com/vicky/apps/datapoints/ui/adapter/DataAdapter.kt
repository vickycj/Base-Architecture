package com.vicky.apps.datapoints.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R

class DataAdapter(val listenerAdapter: RecyclerViewClickListenerAdapter) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v,listenerAdapter)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {



    }
    class DataViewHolder(v:View,val listenerAdapter: RecyclerViewClickListenerAdapter): RecyclerView.ViewHolder(v),View.OnClickListener{

        init {
          //  image_view.setOnClickListener { this }
        }
        override fun onClick(v: View?) {

        }

    }
}