package com.ferrymen.user.data.api

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.user.data.protocol.*
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

    @POST("user/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdReq): Observable<BaseResp<String>>

    @POST("user/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq): Observable<BaseResp<String>>
}