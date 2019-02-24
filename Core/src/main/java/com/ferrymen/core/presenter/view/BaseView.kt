package com.ferrymen.core.presenter.view

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text: String)
}