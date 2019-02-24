package com.ferrymen.user.presenter

import com.ferrymen.baselibrary.presenter.BasePresenter
import com.ferrymen.user.presenter.view.RegisterView

class RegisterPresenter: BasePresenter<RegisterView>() {
    fun reister(mobile: String, verifyCode: String) {
        // 业务逻辑
        mView.onRegisterResult(true);
    }
}