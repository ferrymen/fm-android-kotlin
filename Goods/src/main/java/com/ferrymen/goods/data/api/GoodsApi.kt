package com.ferrymen.goods.data.api

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.goods.data.protocol.GetGoodsDetailReq
import com.ferrymen.goods.data.protocol.GetGoodsListByKeywordReq
import com.ferrymen.goods.data.protocol.GetGoodsListReq
import com.ferrymen.goods.data.protocol.Goods
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResp<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResp<Goods>>
}
