package com.ferrymen.provider.common

import com.ferrymen.core.common.BaseConstant
import com.ferrymen.core.widgets.AppPrefsUtils

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method: () -> Unit) {
//    if (isLogined()) {
    if (true) {
        method()
    } else {
//        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}