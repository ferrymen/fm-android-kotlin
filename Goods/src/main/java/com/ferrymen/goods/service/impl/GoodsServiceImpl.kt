package com.ferrymen.goods.service.impl

import com.ferrymen.core.ext.convert
import com.ferrymen.goods.data.protocol.Goods
import com.ferrymen.goods.data.repository.GoodsRepository
import com.ferrymen.goods.service.GoodsService
import rx.Observable
import javax.inject.Inject

class GoodsServiceImpl @Inject constructor(): GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

}