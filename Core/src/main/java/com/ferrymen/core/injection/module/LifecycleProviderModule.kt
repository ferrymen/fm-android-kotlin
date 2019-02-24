package com.ferrymen.core.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class LifecycleModule(private val activity: Activity) {
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}