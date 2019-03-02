package com.ferrymen.user.presenter

import com.ferrymen.core.ext.execute
import com.ferrymen.core.presenter.BasePresenter
import com.ferrymen.core.rx.BaseSubscriber
import com.ferrymen.user.service.impl.UserServiceImpl
import com.ferrymen.user.presenter.view.RegisterView
import com.ferrymen.user.presenter.view.UserInfoView
import com.ferrymen.user.service.UserService
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService
}