package com.ferrymen.user.data.protocol

data class ForgetPwdReq(val mobile: String, val verifyCode: String) {
}