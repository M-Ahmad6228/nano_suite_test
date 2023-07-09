package com.test.nano_suite.dependency_injection.di.component

import com.test.nano_suite.dependency_injection.di.module.ActivityModule
import com.test.nano_suite.dependency_injection.di.scope.ActivityScope
import com.test.nano_suite.activities.Splash
import com.test.nano_suite.activities.login.Login
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(activity: Splash)
    fun inject(activity: Login)

}