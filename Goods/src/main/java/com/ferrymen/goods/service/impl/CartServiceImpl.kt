package com.ferrymen.goods.service.impl

import com.ferrymen.core.ext.convert
import com.ferrymen.core.ext.convertBoolean
import com.ferrymen.goods.data.protocol.AddCartRes
import com.ferrymen.goods.data.protocol.BaseRes
import com.ferrymen.goods.data.protocol.CartGoods
import com.ferrymen.goods.data.protocol.SubmitCartRes
import com.ferrymen.goods.data.repository.CartRepository
import com.ferrymen.goods.service.CartService
import rx.Observable
import javax.inject.Inject

class CartServiceImpl @Inject constructor(): CartService {

    @Inject
    lateinit var repository: CartRepository

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<AddCartRes> {
        return repository.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).convert()
    }

    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    override fun deleteCartList(list: List<Int>): Observable<BaseRes> {
        return repository.deleteCartList(list).convert()
    }

    override fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<SubmitCartRes> {
        return repository.submitCart(list, totalPrice).convert()
    }

}