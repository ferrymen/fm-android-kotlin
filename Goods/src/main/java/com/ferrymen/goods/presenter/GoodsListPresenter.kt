package com.ferrymen.goods.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.presenter.view.GoodsListView
import com.ferrymen.goods.service.GoodsService
import javax.inject.Inject

class GoodsListPresenter @Inject constructor(): BasePresenter<GoodsListView>() {
    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        goodsService
                .getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        super.onNext(t)
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }
}