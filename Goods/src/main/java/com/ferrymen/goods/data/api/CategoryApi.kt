package com.ferrymen.goods.data.api

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.data.repository.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface CategoryApi {
    /**
     * mock db.json
     *   "userRegister": {
     *       "status": 0,
     *       "message": "接口调用成功",
     *       "data": "" // BaseResp<String>
     *       }
     */
    @POST("category/getCategory")
    fun getCategory(@Body reqGet: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}