package com.ferrymen.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ferrymen.core.common.AppManager
import com.ferrymen.core.ext.enable
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.user.R
import com.ferrymen.user.injection.component.DaggerUserComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.presenter.RegisterPresenter
import com.ferrymen.user.presenter.UserInfoPresenter
import com.ferrymen.user.presenter.view.RegisterView
import com.ferrymen.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class UserInfoActivity : BaseMVPActivity<UserInfoPresenter>(), UserInfoView {
    private var pressTime: Long = 0
    override fun injectComponent() {
        // inject done
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
    }

    private fun initView() {
    }
}

