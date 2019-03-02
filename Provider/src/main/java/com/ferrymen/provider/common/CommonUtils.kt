package com.ferrymen.provider.common

import com.ferrymen.core.common.BaseConstant
import com.ferrymen.core.widgets.AppPrefsUtils

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}