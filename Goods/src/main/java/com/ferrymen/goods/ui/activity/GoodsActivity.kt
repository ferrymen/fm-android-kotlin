package com.ferrymen.goods.ui.activity

import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.presenter.GoodsListPresenter
import com.ferrymen.goods.presenter.view.GoodsListView

class GoodsActivity: BaseMVPActivity<GoodsListPresenter>(), GoodsListView {
    override fun injectComponent() {
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
    }
}