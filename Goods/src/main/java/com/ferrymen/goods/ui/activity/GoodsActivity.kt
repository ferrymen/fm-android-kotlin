package com.ferrymen.goods.ui.activity

import android.os.Bundle
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.injection.component.DaggerGoodsComponent
import com.ferrymen.goods.injection.module.GoodsModule
import com.ferrymen.goods.presenter.GoodsListPresenter
import com.ferrymen.goods.presenter.view.GoodsListView

class GoodsActivity: BaseMVPActivity<GoodsListPresenter>(), GoodsListView {
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
    }
}