package com.ferrymen.user.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.user.service.impl.UserServiceImpl
import com.ferrymen.user.presenter.view.RegisterView
import com.ferrymen.user.service.UserService
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class ForgetPwdPresenter @Inject constructor(): BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserService

    fun reister(mobile: String, pwd: String, verifyCode: String) {
        // 业务逻辑
//        var userService = UserServiceImpl()

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        userService
                .register(mobile, pwd, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        if (t)
                        mView.onRegisterResult("注册成功")
                    }
                }, lifecycleProvider)
    }
}