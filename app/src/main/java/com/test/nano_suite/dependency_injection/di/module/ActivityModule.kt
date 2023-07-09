package com.test.nano_suite.dependency_injection.di.module

import com.test.nano_suite.dependency_injection.base.BaseActivity
import dagger.Module

@Module
class ActivityModule(activity: BaseActivity<*, *>) {
    private var activity: BaseActivity<*, *>

    init {
        this.activity = activity
    }

//    @Provides
//    fun provideSettingsModel(
//        dataManager: DataManager,
//        schedulerProvider: SchedulerProvider,
//        resourceProvider: ResourceProvider
//    ): StartViewModel {
//        return StartViewModel(dataManager, schedulerProvider, resourceProvider)
//    }

}