package com.vicky.apps.datapoints.ui.viewmodel

import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
) {
}