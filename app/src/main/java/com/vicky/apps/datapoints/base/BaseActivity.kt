package com.vicky.apps.datapoints.base

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject
import dagger.android.AndroidInjection
import android.os.Bundle
import androidx.annotation.Nullable
import io.reactivex.disposables.CompositeDisposable


 open class BaseActivity : AppCompatActivity(), HasFragmentInjector {

    protected val compositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? {
        return fragmentInjector
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }



    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }


}