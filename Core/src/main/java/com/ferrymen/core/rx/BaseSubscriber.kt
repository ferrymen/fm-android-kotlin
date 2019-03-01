package com.ferrymen.core.rx

import com.ferrymen.core.presenter.view.BaseView
import rx.Subscriber

open class BaseSubscriber<T>(val baseView: BaseView): Subscriber<T>() {
    override fun onNext(t: T) {
        baseView.hideLoading()
    }

    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError((e.msg))
        } else {
            throw e!!
        }
    }
}