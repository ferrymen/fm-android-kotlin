package com.ferrymen.goods.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseActivity
import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.core.utils.YuanFenConverter
import com.ferrymen.core.widgets.BannerImageLoader
import com.ferrymen.goods.R
import com.ferrymen.goods.common.GoodsConstant
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.event.AddCartEvent
import com.ferrymen.goods.event.GoodsDetailImageEvent
import com.ferrymen.goods.event.SkuChangedEvent
import com.ferrymen.goods.event.UpdateCartSizeEvent
import com.ferrymen.goods.injection.component.DaggerGoodsComponent
import com.ferrymen.goods.injection.module.GoodsModule
import com.ferrymen.goods.presenter.GoodsDetailPresenter
import com.ferrymen.goods.presenter.view.GoodsDetailView
import com.ferrymen.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.support.v4.toast

/*
    商品分类 Fragment
 */
class GoodsDetailTabOneFragment : BaseMVPFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView
    private var mCurGoods:Goods? = null

    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

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
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as BaseActivity).contentView, Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,0,0)
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
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
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
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

        mSkuPop.setSkuData(result.goodsSku)

    }

    private fun initObserve () {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
  初始化缩放动画
*/
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /*
    加入购物车
 */
    private fun addCart(){
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
            )
        }

    }

    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

}
