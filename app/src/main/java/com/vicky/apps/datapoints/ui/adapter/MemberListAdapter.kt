package com.vicky.apps.datapoints.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.Member


class MemberListAdapter constructor(var memberList: List<com.vicky.apps.datapoints.ui.model.Member>): RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.member_child_list,parent,false)
        return MemberViewHolder(v)
    }

    override fun getItemCount(): Int = memberList.size

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.name.text = memberList[position].name.first + " " +memberList[position].name.last
        holder.age.text = "Age: " + memberList[position].age
        holder.email.text = memberList[position].email
        holder.mobile.text = memberList[position].phone

    }

    fun updatedata(memberList: List<Member>){
        this.memberList = memberList
        notifyDataSetChanged()
    }


    class MemberViewHolder(v: View): RecyclerView.ViewHolder(v){
        val name:TextView = v.findViewById(R.id.memberName)
        val age:TextView = v.findViewById(R.id.age)
        val email:TextView = v.findViewById(R.id.email)
        val mobile:TextView = v.findViewById(R.id.mobile)
    }
}