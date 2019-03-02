package com.ferrymen.fmshop.ui.activity

import android.os.Bundle
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseActivity
import com.ferrymen.fmshop.R
import com.ferrymen.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}