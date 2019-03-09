package com.ferrymen.goods.injection.component

import com.ferrymen.core.injection.PerComponentScope
import com.ferrymen.core.injection.component.ActivityComponent
import com.ferrymen.goods.injection.module.CartModule
import com.ferrymen.goods.injection.module.CategoryModule
import com.ferrymen.goods.injection.module.GoodsModule
import com.ferrymen.goods.ui.activity.GoodsActivity
import com.ferrymen.goods.ui.fragment.CategoryFragment
import com.ferrymen.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(GoodsModule::class, CartModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}