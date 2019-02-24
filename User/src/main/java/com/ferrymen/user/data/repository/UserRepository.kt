package com.ferrymen.user.data.repository

import com.ferrymen.core.data.net.RetrofitFactory
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.user.data.api.UserApi
import com.ferrymen.user.data.protocol.RegisterReq
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject  constructor() {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .register(RegisterReq(mobile, verifyCode, pwd))
    }
}