package com.ferrymen.goods.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.presenter.view.CategoryView
import com.ferrymen.goods.service.CategoryService
import javax.inject.Inject

class CategoryPresenter @Inject constructor(): BasePresenter<CategoryView>() {
    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId: Int) {

        if (!checkNetWork()) {
//            println("网络不可用")
            return
        }

        mView.showLoading()
        categoryService
                .getCategory(parentId)
                .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        super.onNext(t)
                        mView.onGetCategoryResult(t)
                    }
                }, lifecycleProvider)
    }
}