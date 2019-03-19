package com.vicky.apps.datapoints.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.DataFields
import kotlinx.android.synthetic.main.recycler_child_view.view.*

class DataAdapter(var dataFields: List<DataFields>, val listenerAdapter: RecyclerViewClickListenerAdapter) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_child_view,parent,false)
        return DataViewHolder(v,listenerAdapter)
    }

    override fun getItemCount(): Int = dataFields.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.year_view.text = dataFields[position].year
        holder.total_amount_view.text = dataFields[position].volume_of_mobile_data.toString()

            if(dataFields[position].hasLowest_quarter){
                holder.image_view.visibility = View.VISIBLE
            }else{
                holder.image_view.visibility = View.INVISIBLE
            }
        holder.lowest_quarter.text = dataFields[position].lowest_quarter

    }


    fun updateData(dataFields: List<DataFields>){
        this.dataFields = dataFields
        notifyDataSetChanged()
    }



    class DataViewHolder(v:View,val listenerAdapter: RecyclerViewClickListenerAdapter): RecyclerView.ViewHolder(v),View.OnClickListener{

        val year_view: TextView = v.year_tv
        val total_amount_view: TextView = v.total_view
        val image_view: ImageView = v.drop_icon
        val lowest_quarter: TextView = v.low_quarter_tv


        init {
            image_view.setOnClickListener { this }
        }
        override fun onClick(v: View?) {

        }

    }
}