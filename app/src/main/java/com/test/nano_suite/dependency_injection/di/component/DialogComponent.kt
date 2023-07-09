package com.test.nano_suite.dependency_injection.di.component

import com.test.nano_suite.dependency_injection.di.module.DialogModule
import com.test.nano_suite.dependency_injection.di.scope.DialogScope
import dagger.Component

@DialogScope
@Component(modules = [DialogModule::class], dependencies = [AppComponent::class])
interface DialogComponent {
//    fun inject(dialog: RateUsDialog?)
}