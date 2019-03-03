package com.ferrymen.goods.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.goods.data.protocol.Goods

interface GoodsListView: BaseView {
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}