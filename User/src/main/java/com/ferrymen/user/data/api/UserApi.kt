package com.ferrymen.user.data.api

import com.ferrymen.core.data.protocol.BaseResp
import com.ferrymen.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {
    @POST("user/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>
}