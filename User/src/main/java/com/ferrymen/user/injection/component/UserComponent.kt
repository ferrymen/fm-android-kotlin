package com.ferrymen.user.injection.component

import com.ferrymen.core.injection.PerComponentScope
import com.ferrymen.core.injection.component.ActivityComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.ui.activity.*
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}