package com.ferrymen.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.goods.R
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.injection.component.DaggerCategoryComponent
import com.ferrymen.goods.injection.module.CategoryModule
import com.ferrymen.goods.presenter.CategoryPresenter
import com.ferrymen.goods.presenter.view.CategoryView
import com.ferrymen.goods.ui.adapter.SecondCategoryAdapter
import com.ferrymen.goods.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/*
    商品分类 Fragment
 */
class CategoryFragment : BaseMVPFragment<CategoryPresenter>(), CategoryView {
    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        result?.let {
            if (it[0].parentId == 0) {
                topAdapter.setData(it)
                mPresenter.getCategory(it[0].id)
            } else {
                secondAdapter.setData(it)
//                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        }
    }


    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
            }
        })
    }

    private fun loadData() {
        mPresenter.getCategory(0)
    }

}
