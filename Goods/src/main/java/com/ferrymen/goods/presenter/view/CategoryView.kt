package com.ferrymen.goods.presenter.view

import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.goods.data.protocol.Category

interface CategoryView: BaseView {
    fun onGetCategoryResult(result: MutableList<Category>?)
}