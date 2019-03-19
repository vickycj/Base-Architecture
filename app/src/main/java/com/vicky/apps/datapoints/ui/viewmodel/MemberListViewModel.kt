package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository

class MemberListViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
): ViewModel()