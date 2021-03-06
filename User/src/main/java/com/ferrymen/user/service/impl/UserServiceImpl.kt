package com.ferrymen.user.service.impl

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.core.ext.convert
import com.ferrymen.core.ext.convertBoolean
import com.ferrymen.core.rx.BaseException
import com.ferrymen.core.rx.BaseFuncBoolean
import com.ferrymen.user.data.protocol.UserInfo
import com.ferrymen.user.data.repository.UserRepository
import com.ferrymen.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {

    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
//        val repository = UserRepository()

        return repository
                .register(mobile, pwd, verifyCode)
                .convertBoolean()
    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository
                .login(mobile, pwd, pushId)
                .convert()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository
                .forgetPwd(mobile, verifyCode)
                .convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository
                .resetPwd(mobile, pwd)
                .convertBoolean()
    }

    override fun editUser(userIcon: String?, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return repository
                .editUser(userIcon, userName, userGender, userSign)
                .convert()
    }

}