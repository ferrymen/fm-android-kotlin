package com.ferrymen.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ext.setVisible
import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.core.utils.YuanFenConverter
import com.ferrymen.core.widgets.AppPrefsUtils
import com.ferrymen.goods.R
import com.ferrymen.goods.common.GoodsConstant
import com.ferrymen.goods.data.protocol.CartGoods
import com.ferrymen.goods.event.CartAllCheckedEvent
import com.ferrymen.goods.event.UpdateCartSizeEvent
import com.ferrymen.goods.event.UpdateTotalPriceEvent
import com.ferrymen.goods.injection.component.DaggerCartComponent
import com.ferrymen.goods.injection.module.CartModule
import com.ferrymen.goods.presenter.CartListPresenter
import com.ferrymen.goods.presenter.view.CartListView
import com.ferrymen.goods.ui.adapter.CartGoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

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
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        mAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mAdapter

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isSelected
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        mDeleteBtn.onClick {
            var cartIdList: MutableList<Int> = arrayListOf()

            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) {it.id}

            if (cartIdList.size == 0) {
                toast("请选择要删除的数据")
            } else {
                mPresenter.deleteCartList(cartIdList)
            }
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
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result!!.size)
        Bus.send(UpdateCartSizeEvent())

        updateTotalPrice()
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


    private fun refreshEditStatus() {
        var isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)
    }

    override fun onDeleteCartResult(result: Boolean) {
        toast("删除成功")
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}
