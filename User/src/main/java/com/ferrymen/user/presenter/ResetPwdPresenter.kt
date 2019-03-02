package com.ferrymen.user.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.user.presenter.view.ResetPwdView
import com.ferrymen.user.service.UserService
import javax.inject.Inject

class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {
        // 业务逻辑
//        var userService = UserServiceImpl()

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        userService
                .resetPwd(mobile, pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        super.onNext(t)
                        if (t)
                        mView.onResetPwdResult("重置密码成功")
                    }
                }, lifecycleProvider)
    }
}