package com.ferrymen.user.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.user.service.impl.UserServiceImpl
import com.ferrymen.user.presenter.view.RegisterView
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {
    fun reister(mobile: String, verifyCode: String, pwd: String) {
        // 业务逻辑
        var userService = UserServiceImpl()
        userService
                .register(mobile, verifyCode, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
    }

    fun login(mobile: String, pwd: String) {
        // 业务逻辑
        var userService = UserServiceImpl()
        userService
                .register(mobile, "", pwd)
                .execute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        mView.onRegisterResult(t)
                    }
                })
    }
}