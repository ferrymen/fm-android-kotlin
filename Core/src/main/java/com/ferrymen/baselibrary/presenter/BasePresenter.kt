package com.ferrymen.baselibrary.presenter

import com.ferrymen.baselibrary.presenter.view.BaseView

open class BasePresenter<T: BaseView> {
    lateinit var mView: T
}