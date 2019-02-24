package com.ferrymen.core.rx

import rx.Subscriber

open class BaseSubscriber<T>: Subscriber<T>() {
    override fun onNext(t: T) {
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }
}