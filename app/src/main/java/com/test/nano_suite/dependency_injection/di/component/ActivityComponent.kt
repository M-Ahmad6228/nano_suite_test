package com.test.nano_suite.dependency_injection.di.component

import com.test.nano_suite.dependency_injection.di.module.ActivityModule
import com.test.nano_suite.dependency_injection.di.scope.ActivityScope
import com.test.nano_suite.activities.login.Login
import com.test.nano_suite.activities.main.MainActivity
import com.test.nano_suite.activities.product_detail.ProductDetail
import com.test.nano_suite.activities.splash.Splash
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(activity: Splash)
    fun inject(activity: Login)
    fun inject(activity: MainActivity)
    fun inject(activity: ProductDetail)

}