package com.ferrymen.user.presenter.view

import com.ferrymen.core.presenter.view.BaseView

interface RegisterView: BaseView {
    fun onRegisterResult(result: String)
}