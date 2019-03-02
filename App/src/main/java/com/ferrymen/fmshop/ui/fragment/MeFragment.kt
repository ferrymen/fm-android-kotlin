package com.ferrymen.fmshop.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferrymen.core.ui.fragment.BaseFragment
import com.ferrymen.core.widgets.BannerImageLoader
import com.ferrymen.fmshop.R
import com.ferrymen.fmshop.common.*
import com.ferrymen.fmshop.ui.adapter.HomeDiscountAdapter
import com.ferrymen.fmshop.ui.adapter.TopicAdapter
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.find

class MeFragment: BaseFragment() {
//    private lateinit var mHomeBanner: Banner
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
}

}