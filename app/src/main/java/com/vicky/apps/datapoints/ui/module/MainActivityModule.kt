package com.vicky.apps.datapoints.ui.module

import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    fun provideViewModel(repository: Repository,
                         schedulerProvider: SchedulerProvider
    )
            = MainViewModel(repository, schedulerProvider)
}