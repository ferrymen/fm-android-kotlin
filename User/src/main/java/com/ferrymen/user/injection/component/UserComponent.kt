package com.ferrymen.user.injection.component

import com.ferrymen.core.injection.PerComponentScope
import com.ferrymen.core.injection.component.ActivityComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.ui.activity.ForgetPwdActivity
import com.ferrymen.user.ui.activity.LoginActivity
import com.ferrymen.user.ui.activity.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
}