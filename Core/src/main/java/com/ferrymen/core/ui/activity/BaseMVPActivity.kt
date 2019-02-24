package com.ferrymen.core.ui.activity

import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.presenter.view.BaseView
import javax.inject.Inject

open class BaseMVPActivity<T: BasePresenter<*>>() : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter: T
}