package com.ferrymen.core.injection.component

import android.app.Activity
import android.content.Context
import com.ferrymen.core.injection.ActivityScope
import com.ferrymen.core.injection.module.ActivityModule
import com.ferrymen.core.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity
}