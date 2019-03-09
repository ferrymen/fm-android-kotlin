package com.ferrymen.goods.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.goods.data.protocol.BaseRes
import com.ferrymen.goods.data.protocol.CartGoods
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.data.protocol.SubmitCartRes
import com.ferrymen.goods.presenter.view.CartListView
import com.ferrymen.goods.service.CartService
import rx.Observable
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

    fun deleteCartList(list: List<Int>) {

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        cartService
                .deleteCartList(list)
                .execute(object : BaseSubscriber<BaseRes>(mView) {
                    override fun onNext(t: BaseRes) {
                        super.onNext(t)
                        mView.onDeleteCartResult(t.isSuccessed)
                    }
                }, lifecycleProvider)
    }

    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        cartService
                .submitCart(list, totalPrice)
                .execute(object : BaseSubscriber<SubmitCartRes>(mView) {
                    override fun onNext(t: SubmitCartRes) {
                        super.onNext(t)
                        mView.onSubmitCartResult(t.order)
                    }
                }, lifecycleProvider)
    }

}