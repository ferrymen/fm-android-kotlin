package com.ferrymen.goods.data.repository

import com.ferrymen.core.data.net.RetrofitFactory
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.goods.data.api.CategoryApi
import com.ferrymen.goods.data.protocol.Category
import rx.Observable
import javax.inject.Inject

class CategoryRepository @Inject  constructor() {
    fun getCategory(parentId: Int): Observable<BaseResp<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}