package com.ferrymen.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ferrymen.core.common.AppManager
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.user.R
import com.ferrymen.user.injection.component.DaggerUserComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.presenter.RegisterPresenter
import com.ferrymen.user.presenter.view.RegisterView
import com.kotlin.base.widgets.VerifyButton
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterView {
    private var pressTime: Long = 0
    override fun injectComponent() {
        // inject done
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        mPresenter = RegisterPresenter()
//        mPresenter.mView = this
//        initInjection()

        btnRegister.onClick {
            //                Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show()
//            toast("Hi there!")
//            toast("注册")
            mPresenter.reister(
                    mMobileEt.text.toString(),
                    mVerifyCodeEt.text.toString(),
                    mPwdEt.text.toString())
        }

        mGetVerifyCodeBtn.onClick {
            mGetVerifyCodeBtn.requestSendVerifyNumber()
        }

//            mGetVerifyCodeBtn.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
//                override fun onClick() {
//                    toast("获取验证码")
//                }
//
//            })
//
//            mGetVerifyCodeBtn.requestSendVerifyNumber()
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()

        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

//    private fun initInjection() {
//        // inject done
//        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
//        mPresenter.mView = this
//    }
}
