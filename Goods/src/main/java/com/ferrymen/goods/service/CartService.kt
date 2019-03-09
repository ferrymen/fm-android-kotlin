package com.ferrymen.goods.service
import rx.Observable

interface CartService {
    /*
    添加商品到购物车
 */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>
}