package com.ferrymen.goods.ui.fragment

import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.injection.component.DaggerCategoryComponent
import com.ferrymen.goods.injection.module.CategoryModule
import com.ferrymen.goods.presenter.CategoryPresenter
import com.ferrymen.goods.presenter.view.CategoryView

/*
    商品分类 Fragment
 */
class CategoryFragment : BaseMVPFragment<CategoryPresenter>(), CategoryView {
    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
    }

}
