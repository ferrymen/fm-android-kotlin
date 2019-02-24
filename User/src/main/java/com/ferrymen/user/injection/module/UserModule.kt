package com.ferrymen.user.injection.module

import com.ferrymen.user.service.UserService
import com.ferrymen.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @Provides
    fun providersUserService(service: UserServiceImpl): UserService {
        return service
    }
}