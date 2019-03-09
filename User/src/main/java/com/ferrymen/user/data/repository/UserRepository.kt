package com.ferrymen.user.data.repository

import com.ferrymen.core.data.net.RetrofitFactory
import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.user.data.api.UserApi
import com.ferrymen.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

class UserRepository @Inject  constructor() {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<BaseRes>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .login(LoginReq(mobile, pwd, pushId))
    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResp<BaseRes>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile, verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<BaseRes>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile, pwd))
    }

    fun editUser(userIcon: String?, userName: String, userGender: String, userSign: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance
                .create(UserApi::class.java)
                .editUser(EditUserReq(userIcon, userName, userGender, userSign))
    }
}