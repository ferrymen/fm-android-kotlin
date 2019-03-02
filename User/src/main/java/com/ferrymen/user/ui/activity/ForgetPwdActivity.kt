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
import com.ferrymen.user.presenter.ForgetPwdPresenter
import com.ferrymen.user.presenter.RegisterPresenter
import com.ferrymen.user.presenter.view.ForgetPwdView
import com.ferrymen.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.toast

class ForgetPwdActivity : BaseMVPActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    private var pressTime: Long = 0
    override fun injectComponent() {
        // inject done
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {
        mNextBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)

        mNextBtn.enable(mMobileEt) { isBtnEnable() }
        mNextBtn.enable(mVerifyCodeEt) { isBtnEnable() }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功！")
            }
            R.id.mNextBtn -> {
                mPresenter.forgetPwd(
                    mMobileEt.text.toString(),
                    mVerifyCodeEt.text.toString()
                )
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
    }
}
