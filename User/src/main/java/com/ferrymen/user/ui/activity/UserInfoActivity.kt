package com.ferrymen.user.ui.activity

import android.os.Bundle
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.user.R
import com.ferrymen.user.injection.component.DaggerUserComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.presenter.UserInfoPresenter
import com.ferrymen.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
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
        mUserIconView.onClick {
            showAlertView()
        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                when(position) {
                    0 -> toast("拍照")
                    1 -> toast("相册")
                }
            }

        }).show()
    }
}

