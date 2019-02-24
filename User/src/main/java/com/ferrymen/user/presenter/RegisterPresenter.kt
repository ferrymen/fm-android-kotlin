package com.ferrymen.user.presenter

import com.ferrymen.baselibrary.presenter.BasePresenter
import com.ferrymen.user.presenter.service.impl.UserServiceImpl
import com.ferrymen.user.presenter.view.RegisterView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RegisterPresenter: BasePresenter<RegisterView>() {
    fun reister(mobile: String, verifyCode: String, pwd: String) {
        // 业务逻辑
        var userService = UserServiceImpl()
        userService
                .register(mobile, verifyCode, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object: Subscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
    }
}