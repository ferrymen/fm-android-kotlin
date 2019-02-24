package com.ferrymen.core.ui.activity

import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.presenter.view.BaseView

open class BaseMVPActivity<T: BasePresenter<*>>() : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }
    lateinit var mPresenter: T
}