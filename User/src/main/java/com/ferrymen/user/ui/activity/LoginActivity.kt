package com.ferrymen.user.ui.activity

import android.os.Bundle
import android.view.View
import com.ferrymen.core.ext.enable
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.user.R
import com.ferrymen.user.data.protocol.UserInfo
import com.ferrymen.user.injection.component.DaggerUserComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.presenter.LoginPresenter
import com.ferrymen.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMVPActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    private var pressTime: Long = 0
    override fun injectComponent() {
        // inject done
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        mPresenter = RegisterPresenter()
//        mPresenter.mView = this
//        initInjection()

        initView()
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
//        finish()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPwdEt) { isBtnEnable() }

        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }
}
