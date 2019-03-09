package com.ferrymen.goods.service
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.goods.data.protocol.*
import retrofit2.http.Body
import rx.Observable

interface CartService {
    /*
    添加商品到购物车
 */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<AddCartRes>

    fun getCartList(): Observable<MutableList<CartGoods>?>

    fun deleteCartList(list: List<Int>): Observable<BaseRes>

    /*
        购物车结算
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<SubmitCartRes>
}