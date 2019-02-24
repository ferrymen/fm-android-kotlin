package com.ferrymen.user.injection.component

import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.ui.activity.RegisterActivity
import dagger.Component

@Component(modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity: RegisterActivity)
}