package com.ferrymen.goods.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.goods.data.protocol.CartGoods
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.presenter.view.CartListView
import com.ferrymen.goods.service.CartService
import javax.inject.Inject

class CartListPresenter @Inject constructor(): BasePresenter<CartListView>() {
    @Inject
    lateinit var cartService: CartService

    fun getCartList() {

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        cartService
                .getCartList()
                .execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                        super.onNext(t)
                        mView.onGetCartListResult(t)
                    }
                }, lifecycleProvider)
    }
}