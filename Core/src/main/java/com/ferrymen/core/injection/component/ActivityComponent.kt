package com.ferrymen.core.injection.component

import android.app.Activity
import android.content.Context
import com.ferrymen.core.injection.ActivityScope
import com.ferrymen.core.injection.module.ActivityModule
import com.ferrymen.core.injection.module.AppModule
import com.ferrymen.core.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    // error: com.trello.rxlifecycle.LifecycleProvider<?> cannot be provided without an @Provides- or @Produces-annotated method
    fun lifecycleProvider(): LifecycleProvider<*>
}