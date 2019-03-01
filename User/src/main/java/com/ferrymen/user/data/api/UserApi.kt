package com.ferrymen.user.data.api

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.user.data.protocol.LoginReq
import com.ferrymen.user.data.protocol.RegisterReq
import com.ferrymen.user.data.protocol.UserInfo
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {
    /**
     * mock db.json
     *   "userRegister": {
     *       "status": 0,
     *       "message": "接口调用成功",
     *       "data": "" // BaseResp<String>
     *       }
     */
    @POST("user/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>

    /**
     * mock db.json
     *   "userLogin": {
     *      "status": 0,
     *       "message": "接口调用成功",
     *       "data": {} // BaseResp<UserInfo>
     *       }
     */
    @POST("user/login")
    fun login(@Body req: LoginReq): Observable<BaseResp<UserInfo>>
}