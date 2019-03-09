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
import com.ferrymen.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {
    private var pressTime: Long = 0
    override fun injectComponent() {
        // inject done
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        mPresenter = RegisterPresenter()
//        mPresenter.mView = this
//        initInjection()

        initView()
    }

    private fun initView() {
        mRegisterBtn.onClick(this)
//        mRegisterBtn.onClick {
//            //                Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show()
////            toast("Hi there!")
////            toast("注册")
//            mPresenter.reister(
//                    mMobileEt.text.toString(),
//                    mVerifyCodeEt.text.toString(),
//                    mPwdEt.text.toString())
//        }

        mVerifyCodeBtn.onClick(this)
//        mVerifyCodeBtn.onClick {
//            mVerifyCodeBtn.requestSendVerifyNumber()
//        }

//            mGetVerifyCodeBtn.setOnVerifyBtnClick(object : VerifyButton.OnVerifyBtnClick {
//                override fun onClick() {
//                    toast("获取验证码")
//                }
//
//            })
//
//            mGetVerifyCodeBtn.requestSendVerifyNumber()

//            mRegisterBtn.enable(mMobileEt, { isBtnEnable() })
//            mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
//            mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
//            mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })
        mRegisterBtn.enable(mMobileEt) { isBtnEnable() }
        mRegisterBtn.enable(mVerifyCodeEt) { isBtnEnable() }
        mRegisterBtn.enable(mPwdEt) { isBtnEnable() }
        mRegisterBtn.enable(mPwdConfirmEt) { isBtnEnable() }
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

    override fun onClick(v: View) {
        when(v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功！")
            }
            R.id.mRegisterBtn -> {
                mPresenter.reister(
                    mMobileEt.text.toString(),
                    mPwdEt.text.toString(),
                        mVerifyCodeEt.text.toString()
                )
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
