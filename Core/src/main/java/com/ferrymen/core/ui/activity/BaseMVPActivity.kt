package com.ferrymen.core.ui.activity

import android.os.Bundle
import com.ferrymen.core.common.BaseApplication
import com.ferrymen.core.injection.component.ActivityComponent
import com.ferrymen.core.injection.component.DaggerActivityComponent
import com.ferrymen.core.injection.module.ActivityModule
import com.ferrymen.core.injection.module.LifecycleProviderModule
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.presenter.view.BaseView
import com.ferrymen.core.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

open abstract class BaseMVPActivity<T: BasePresenter<*>>() : BaseActivity(), BaseView {
    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent
                .builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}