package com.vicky.apps.datapoints.di

import com.vicky.apps.datapoints.ui.module.MainActivityModule
import com.vicky.apps.datapoints.ui.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}