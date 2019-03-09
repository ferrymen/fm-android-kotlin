package com.ferrymen.core.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.ferrymen.core.injection.component.AppComponent
import com.ferrymen.core.injection.component.DaggerAppComponent
import com.ferrymen.core.injection.module.AppModule

class BaseApplication: Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        initAppInjection()

        context = this

        //ARouter初始化
        ARouter.openLog()    // 打印日志
        ARouter.openDebug()
        ARouter.init(this)
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule((this))).build()
    }
}