package com.ferrymen.goods.service
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.goods.data.protocol.AddCartRes
import com.ferrymen.goods.data.protocol.CartGoods
import rx.Observable

interface CartService {
    /*
    添加商品到购物车
 */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<AddCartRes>

    fun getCartList(): Observable<MutableList<CartGoods>?>
}