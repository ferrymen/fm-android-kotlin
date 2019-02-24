package com.ferrymen.user.presenter.view

import com.ferrymen.baselibrary.presenter.view.BaseView

interface RegisterView: BaseView {
    fun onRegisterResult(result: Boolean)
}