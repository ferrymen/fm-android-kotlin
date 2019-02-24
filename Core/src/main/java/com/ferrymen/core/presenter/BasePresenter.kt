package com.ferrymen.core.presenter

import com.ferrymen.core.presenter.view.BaseView

open class BasePresenter<T: BaseView> {
    lateinit var mView: T
}