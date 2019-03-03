package com.ferrymen.goods.injection.module

import com.ferrymen.goods.service.GoodsService
import com.ferrymen.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

@Module
class GoodsModule {
    @Provides
    fun providersGoodsService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }
}