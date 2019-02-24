package com.ferrymen.core.injection

import javax.inject.Scope
import kotlin.annotation.MustBeDocumented
import kotlin.annotation.Retention

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerComponentScope