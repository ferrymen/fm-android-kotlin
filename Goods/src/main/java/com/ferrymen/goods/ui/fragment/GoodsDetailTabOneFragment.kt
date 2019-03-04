package com.ferrymen.goods.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseActivity
import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.core.utils.YuanFenConverter
import com.ferrymen.core.widgets.BannerImageLoader
import com.ferrymen.goods.R
import com.ferrymen.goods.common.GoodsConstant
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.event.GoodsDetailImageEvent
import com.ferrymen.goods.injection.component.DaggerGoodsComponent
import com.ferrymen.goods.injection.module.GoodsModule
import com.ferrymen.goods.presenter.GoodsDetailPresenter
import com.ferrymen.goods.presenter.view.GoodsDetailView
import com.ferrymen.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/*
    商品分类 Fragment
 */
class GoodsDetailTabOneFragment : BaseMVPFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView
    private var mCurGoods:Goods? = null

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initSkuPop()
        loadData()
    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as BaseActivity).contentView, Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,0,0)
        }
    }


    private fun loadData() {
        mPresenter.getGoodsDetailList(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /*
    初始化sku弹层
 */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity as Activity)
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

    }

}
