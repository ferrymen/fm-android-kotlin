package com.ferrymen.goods.service.impl

import com.ferrymen.core.ext.convert
import com.ferrymen.goods.data.repository.CartRepository
import com.ferrymen.goods.data.repository.GoodsRepository
import com.ferrymen.goods.service.CartService
import rx.Observable
import javax.inject.Inject

class CartServiceImpl @Inject constructor(): CartService {
    @Inject
    lateinit var repository: CartRepository

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).convert()
    }



}