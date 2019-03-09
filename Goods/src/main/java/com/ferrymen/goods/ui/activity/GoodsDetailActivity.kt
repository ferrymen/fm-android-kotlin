package com.ferrymen.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.eightbitlab.rxbus.Bus
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseActivity
import com.ferrymen.goods.R
import com.ferrymen.goods.event.AddCartEvent
import com.ferrymen.goods.ui.adapter.GoodsDetailVpAdapter
import com.ferrymen.provider.common.afterLogin
import com.ferrymen.provider.common.isLogined
import kotlinx.android.synthetic.main.activity_goods_detail.*

class GoodsDetailActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED

        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)

        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }
    }
}