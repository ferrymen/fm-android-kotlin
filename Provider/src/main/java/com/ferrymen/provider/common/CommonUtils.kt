package com.ferrymen.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.ferrymen.core.common.BaseConstant
import com.ferrymen.core.widgets.AppPrefsUtils
import com.ferrymen.provider.router.RouterPath

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.User.PATH_LOGIN).navigation()
    }
}