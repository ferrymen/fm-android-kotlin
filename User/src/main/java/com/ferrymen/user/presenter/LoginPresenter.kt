package com.ferrymen.user.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.user.data.protocol.UserInfo
import com.ferrymen.user.presenter.view.LoginView
import com.ferrymen.user.service.impl.UserServiceImpl
import com.ferrymen.user.presenter.view.RegisterView
import com.ferrymen.user.service.UserService
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        // 业务逻辑
//        var userService = UserServiceImpl()

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        userService
                .login(mobile, pwd, pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        super.onNext(t)
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)
    }
}