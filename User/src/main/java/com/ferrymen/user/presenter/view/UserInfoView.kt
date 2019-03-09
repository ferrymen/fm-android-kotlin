package com.ferrymen.user.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.user.data.protocol.UserInfo

interface UserInfoView: BaseView {
    /*
        编辑用户资料回调
     */
    fun onEditUserResult(result:UserInfo)
}