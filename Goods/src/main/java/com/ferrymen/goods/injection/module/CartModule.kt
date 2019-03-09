package com.ferrymen.goods.injection.module

import com.ferrymen.goods.service.CartService
import com.ferrymen.goods.service.GoodsService
import com.ferrymen.goods.service.impl.CartServiceImpl
import com.ferrymen.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

@Module
class CartModule {
    @Provides
    fun providersCartService(cartService: CartServiceImpl): CartService {
        return cartService
    }
}