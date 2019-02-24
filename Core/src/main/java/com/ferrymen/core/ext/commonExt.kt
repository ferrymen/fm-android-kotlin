package com.ferrymen.core.ext

import android.view.View
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.core.rx.BaseFunc
import com.ferrymen.core.rx.BaseFuncBoolean
import com.ferrymen.core.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T>Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

fun <T>Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T>Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun  View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}