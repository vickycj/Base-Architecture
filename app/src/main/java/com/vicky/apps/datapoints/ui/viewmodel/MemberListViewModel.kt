package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.Member

class MemberListViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
): ViewModel() {


    private lateinit var memberList :List<Member>
    private var ascendingAge = false
    private var ascendingName = false

    fun getMemberData(): List<Member>{
        return memberList
    }

    fun setMemberData(member: List<Member>){
        this.memberList = member
    }

    fun sortByAge() {
        if(!ascendingAge){
            ascendingAge = true
            memberList =  ArrayList(memberList).sortedBy {
                it.age
            }.toMutableList()
        }else{
            ascendingAge = false
            memberList =  ArrayList(memberList).sortedByDescending {
                it.age
            }.toMutableList()
        }

    }

    fun sortByName() {
        if(!ascendingName){
            ascendingName = true
            memberList =  ArrayList(memberList).sortedBy {
                it.name.first
            }.toMutableList()
        }else{
            ascendingName = false
            memberList =  ArrayList(memberList).sortedByDescending {
                it.name.first
            }.toMutableList()
        }

    }
}