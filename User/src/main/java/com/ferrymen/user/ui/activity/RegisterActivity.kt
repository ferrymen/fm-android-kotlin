package com.ferrymen.user.ui.activity

import android.os.Bundle
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.user.R
import com.ferrymen.user.presenter.RegisterPresenter
import com.ferrymen.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        btnRegister.setOnClickListener {
//            Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show()
//            toast("Hi there!")
//            toast("注册")
            mPresenter.reister("", "", "")
        }
    }
}
