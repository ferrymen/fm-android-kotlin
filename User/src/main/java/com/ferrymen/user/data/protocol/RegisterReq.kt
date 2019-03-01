package com.ferrymen.user.data.protocol

data class RegisterReq(val mobile: String, val pwd: String,  val verifyCode: String) {
}