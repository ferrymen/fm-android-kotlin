package com.ferrymen.core.common

import android.app.Application
import com.ferrymen.core.injection.component.AppComponent
import com.ferrymen.core.injection.component.DaggerAppComponent
import com.ferrymen.core.injection.module.AppModule

class BaseApplication: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule((this))).build()
    }
}