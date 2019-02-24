package com.ferrymen.baselibrary.ui.activity

import android.os.Parcel
import android.os.Parcelable
import com.ferrymen.baselibrary.presenter.BasePresenter
import com.ferrymen.baselibrary.presenter.view.BaseView

open class BaseMVPActivity<T: BasePresenter<*>>() : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }
    lateinit var mPresenter: T
}