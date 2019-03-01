package com.ferrymen.user.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.user.data.protocol.UserInfo

interface LoginView: BaseView {
    fun onLoginResult(result: UserInfo)
}