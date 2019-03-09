package com.ferrymen.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.core.utils.YuanFenConverter
import com.ferrymen.goods.R
import com.ferrymen.goods.data.protocol.CartGoods
import com.ferrymen.goods.event.CartAllCheckedEvent
import com.ferrymen.goods.event.UpdateTotalPriceEvent
import com.ferrymen.goods.injection.component.DaggerCartComponent
import com.ferrymen.goods.injection.component.DaggerCategoryComponent
import com.ferrymen.goods.injection.module.CartModule
import com.ferrymen.goods.injection.module.CategoryModule
import com.ferrymen.goods.presenter.CartListPresenter
import com.ferrymen.goods.presenter.view.CartListView
import com.ferrymen.goods.ui.adapter.CartGoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_cart.*

/*
    商品分类 Fragment
 */
class CartFragment : BaseMVPFragment<CartListPresenter>(), CartListView {

    private lateinit var mAdapter: CartGoodsAdapter
    private var mTotalPrice: Long = 0

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent).cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
        initObserve()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mAdapter

        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isSelected
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }
    }

    private fun loadData() {
        mPresenter.getCartList()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
//            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe {
//                    t: CartAllCheckedEvent -> run {
//                    mAllCheckedCb.isSelected = t.isAllChecked
//                }
                    mAllCheckedCb.isSelected = it.isAllChecked
                    updateTotalPrice()
                }
                .registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    updateTotalPrice()
                }
                .registerInBus(this)
    }

    private fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()

        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}
