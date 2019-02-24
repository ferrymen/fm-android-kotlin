package com.ferrymen.user.service.impl

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.core.rx.BaseException
import com.ferrymen.user.data.repository.UserRepository
import com.ferrymen.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        val repository = UserRepository()

        return repository
                .register(mobile, verifyCode, pwd)
                .flatMap(object: Func1<BaseResp<String>, Observable<Boolean>> {
                    override fun call(t: BaseResp<String>): Observable<Boolean> {
                        if (t.status != 0) {
                            return Observable.error(BaseException(t.status, t.message))
                        }
                        return Observable.just(true)
                    }

                })
    }
}