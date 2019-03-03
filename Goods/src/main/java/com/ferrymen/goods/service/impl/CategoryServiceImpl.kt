package com.ferrymen.goods.service.impl

import com.ferrymen.core.ext.convert
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.data.repository.CategoryRepository
import com.ferrymen.goods.service.CategoryService
import rx.Observable
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor(): CategoryService {

    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }


}