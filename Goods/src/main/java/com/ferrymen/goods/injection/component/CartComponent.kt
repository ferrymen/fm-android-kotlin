package com.ferrymen.goods.injection.component

import com.ferrymen.core.injection.PerComponentScope
import com.ferrymen.core.injection.component.ActivityComponent
import com.ferrymen.goods.injection.module.CartModule
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CartModule::class))
interface CartComponent {
}