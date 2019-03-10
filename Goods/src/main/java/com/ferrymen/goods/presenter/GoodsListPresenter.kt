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

        // 只有在调试模式下，商品页才能触发点击事件的原因是因为showLoading
//        mView.showLoading()
        goodsService
                .getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
//                        super.onNext(t)
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        // 只有在调试模式下，商品页才能触发点击事件的原因是因为showLoading
//        mView.showLoading()
        goodsService
                .getGoodsListByKeyword(keyword, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        super.onNext(t)
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)
    }
}