package com.test.nano_suite.activities.main

import com.test.nano_suite.dependency_injection.base.BaseViewModel
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider

class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    resourceProvider: ResourceProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider, resourceProvider) {
}