package com.ferrymen.user.presenter.service.impl

import com.ferrymen.user.presenter.service.UserService
import rx.Observable

class UserServiceImpl: UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}