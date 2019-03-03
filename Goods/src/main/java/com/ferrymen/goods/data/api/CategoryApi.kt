package com.ferrymen.goods.data.api

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface CategoryApi {
    /**
     * if no "category/getCategory" server
     * retrofit2.adapter.rxjava.HttpException: HTTP 404 Not Found
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}