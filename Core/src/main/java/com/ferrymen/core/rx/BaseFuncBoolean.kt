package com.ferrymen.core.rx

import com.ferrymen.core.common.ResultCode
import com.ferrymen.core.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

class BaseFuncBoolean<T>: Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }

}
