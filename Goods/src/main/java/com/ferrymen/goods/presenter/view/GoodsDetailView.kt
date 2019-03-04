package com.ferrymen.goods.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.goods.data.protocol.Goods

/*
    商品详情 视图回调
 */
interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)
    //加入购物车
}
