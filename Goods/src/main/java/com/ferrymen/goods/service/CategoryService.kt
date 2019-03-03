package com.ferrymen.goods.service
import com.ferrymen.goods.data.protocol.Category
import rx.Observable

interface CategoryService {
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}