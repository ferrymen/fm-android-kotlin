package com.ferrymen.core.ui.activity

import android.os.Bundle
import com.ferrymen.core.common.BaseApplication
import com.ferrymen.core.injection.component.ActivityComponent
import com.ferrymen.core.injection.component.DaggerActivityComponent
import com.ferrymen.core.injection.module.ActivityModule
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.presenter.view.BaseView
import javax.inject.Inject

open class BaseMVPActivity<T: BasePresenter<*>>() : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent
                .builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
}