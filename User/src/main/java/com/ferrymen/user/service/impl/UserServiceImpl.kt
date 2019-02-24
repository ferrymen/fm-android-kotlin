package com.ferrymen.user.service.impl

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.core.ext.convertBoolean
import com.ferrymen.core.rx.BaseException
import com.ferrymen.core.rx.BaseFuncBoolean
import com.ferrymen.user.data.repository.UserRepository
import com.ferrymen.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {
    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
//        val repository = UserRepository()

        return repository
                .register(mobile, verifyCode, pwd)
                .convertBoolean()
    }
}