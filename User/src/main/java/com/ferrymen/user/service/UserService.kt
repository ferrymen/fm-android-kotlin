package com.ferrymen.user.service
import com.ferrymen.user.data.protocol.UserInfo
import rx.Observable

interface UserService {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>
}