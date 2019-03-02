package com.ferrymen.fmshop.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferrymen.core.ui.fragment.BaseFragment
import com.ferrymen.core.widgets.BannerImageLoader
import com.ferrymen.fmshop.R
import com.ferrymen.fmshop.common.*
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find

class HomeFragment: BaseFragment() {
//    private lateinit var mHomeBanner: Banner
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
//        val rootView = inflater.inflate(R.layout.fragment_home, null)
//
//        initBanner(rootView)
//        return rootView
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(
                HOME_BANNER_ONE,
                HOME_BANNER_TWO,
//                HOME_BANNER_THREE,
                HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }

//    private fun initBanner(view: View) {
//        mHomeBanner = view.find(R.id.mHomeBanner)
//        mHomeBanner.setImageLoader(BannerImageLoader())
//        mHomeBanner.setImages(listOf(
//                HOME_BANNER_ONE,
//                HOME_BANNER_TWO,
////                HOME_BANNER_THREE,
//                HOME_BANNER_FOUR))
//        mHomeBanner.setBannerAnimation(Transformer.Accordion)
//        mHomeBanner.setDelayTime(2000)
//        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
//        mHomeBanner.start()
//    }
}