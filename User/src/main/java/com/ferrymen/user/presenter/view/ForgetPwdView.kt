package com.ferrymen.user.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.user.data.protocol.UserInfo

interface ForgetPwdView: BaseView {
    fun onForgetPwdResult(result: String)
}