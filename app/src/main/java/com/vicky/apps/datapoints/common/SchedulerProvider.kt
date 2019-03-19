package com.vicky.apps.datapoints.common

import io.reactivex.*

open class SchedulerProvider(val io: Scheduler, val  mainThread: Scheduler?) {
    fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
        return { observable: Observable<T> ->
            observable.subscribeOn(io)
                .observeOn(mainThread)
        }
    }

    fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {
        return { single: Single<T> ->
            single.subscribeOn(io)
                .observeOn(mainThread)
        }
    }

    fun getSchedulersForCompletable(): (Completable) -> Completable {
        return { completable: Completable ->
            completable.subscribeOn(io)
                .observeOn(mainThread)
        }
    }

    fun <T> getSchedulersForFlowable(): (Flowable<T>) -> Flowable<T> {
        return { flowable: Flowable<T> ->
            flowable.subscribeOn(io)
                .observeOn(mainThread)
        }
    }
}
