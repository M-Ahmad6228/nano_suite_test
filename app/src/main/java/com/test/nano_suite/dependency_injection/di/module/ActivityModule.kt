package com.test.nano_suite.dependency_injection.di.module

import com.test.nano_suite.activities.login.LoginViewModel
import com.test.nano_suite.dependency_injection.base.BaseActivity
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(activity: BaseActivity<*, *>) {
    private var activity: BaseActivity<*, *>

    init {
        this.activity = activity
    }

    @Provides
    fun loginViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider,
        resourceProvider: ResourceProvider
    ): LoginViewModel {
        return LoginViewModel(dataManager, schedulerProvider, resourceProvider)
    }

}