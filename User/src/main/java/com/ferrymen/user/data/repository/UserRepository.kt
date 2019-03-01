package com.ferrymen.user.data.repository

import com.ferrymen.core.data.net.RetrofitFactory
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.user.data.api.UserApi
import com.ferrymen.user.data.protocol.LoginReq
import com.ferrymen.user.data.protocol.RegisterReq
import com.ferrymen.user.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject  constructor() {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .login(LoginReq(mobile, pwd, pushId))
    }
}