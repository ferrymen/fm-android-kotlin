package com.ferrymen.goods.service
import com.ferrymen.goods.data.protocol.Goods
import rx.Observable

interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>

    /*
    获取商品详情
 */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}