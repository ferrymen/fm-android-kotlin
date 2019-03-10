package com.ferrymen.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferrymen.core.ext.setVisible
import com.ferrymen.core.ui.fragment.BaseMVPFragment
import com.ferrymen.goods.R
import com.ferrymen.goods.data.protocol.Category
import com.ferrymen.goods.injection.component.DaggerCategoryComponent
import com.ferrymen.goods.injection.module.CategoryModule
import com.ferrymen.goods.presenter.CategoryPresenter
import com.ferrymen.goods.presenter.view.CategoryView
import com.ferrymen.goods.ui.activity.GoodsActivity
import com.ferrymen.goods.ui.adapter.SecondCategoryAdapter
import com.ferrymen.goods.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import com.ferrymen.core.ui.adapter.BaseRecyclerViewAdapter
import com.ferrymen.goods.common.GoodsConstant
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/*
    商品分类 Fragment
 */
class CategoryFragment : BaseMVPFragment<CategoryPresenter>(), CategoryView {
    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    private lateinit var secondAdapter: SecondCategoryAdapter

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
//        result?.let {
//            if (it[0].parentId == 0) {
//                it[0].isSelected = true
//                topAdapter.setData(it)
//                loadData(it[0].id)
//            } else {
//                secondAdapter.setData(it)
//                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
//            }
//        }

        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
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

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID to item.id)
            }
        })
    }

    private fun loadData(parentId: Int = 0) {
        mPresenter.getCategory(parentId)
    }

}
