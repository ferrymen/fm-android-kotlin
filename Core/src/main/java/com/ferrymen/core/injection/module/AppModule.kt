package com.ferrymen.core.injection.module

import android.content.Context
import com.ferrymen.core.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class AppModule(private val context: BaseApplication) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}