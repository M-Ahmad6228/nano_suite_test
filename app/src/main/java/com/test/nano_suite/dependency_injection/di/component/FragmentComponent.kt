package com.test.nano_suite.dependency_injection.di.component

import com.test.nano_suite.dependency_injection.di.module.FragmentModule
import com.test.nano_suite.dependency_injection.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [AppComponent::class])
interface FragmentComponent {
//    fun inject(fragment: LoginFragment)
}
