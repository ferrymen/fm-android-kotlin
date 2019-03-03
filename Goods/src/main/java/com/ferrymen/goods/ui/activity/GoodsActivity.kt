package com.ferrymen.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.ferrymen.core.ext.startLoading
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.goods.R
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.injection.component.DaggerGoodsComponent
import com.ferrymen.goods.injection.module.GoodsModule
import com.ferrymen.goods.presenter.GoodsListPresenter
import com.ferrymen.goods.presenter.view.GoodsListView
import com.ferrymen.goods.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*

class GoodsActivity: BaseMVPActivity<GoodsListPresenter>(), GoodsListView {
    private lateinit var mGoodsAdapter: GoodsAdapter

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        if (result != null && result.size > 0) {
            mGoodsAdapter.setData(result)
//            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
//            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        loadData()
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter
    }


    private fun loadData() {
//        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra("categoryId", 1), 1)
    }
}