package com.ferrymen.goods.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.goods.data.protocol.CartGoods

interface CartListView: BaseView {
    fun onGetCartListResult(result: MutableList<CartGoods>?)

    fun onDeleteCartResult(result: Boolean)
}