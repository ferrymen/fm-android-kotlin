package com.ferrymen.user.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.user.service.impl.UserServiceImpl
import com.ferrymen.user.presenter.view.RegisterView
import com.ferrymen.user.service.UserService
import com.kotlin.base.utils.NetWorkUtils
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun reister(mobile: String, verifyCode: String, pwd: String) {
        // 业务逻辑
//        var userService = UserServiceImpl()

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        userService
                .register(mobile, verifyCode, pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        if (t)
                        mView.onRegisterResult("注册成功")
                    }
                }, lifecycleProvider)
    }

    fun login(mobile: String, pwd: String) {
        // 业务逻辑
        var userService = UserServiceImpl()
        userService
                .register(mobile, "", pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        if (t)
                        mView.onRegisterResult("登录成功")
                    }
                }, lifecycleProvider)
    }
}