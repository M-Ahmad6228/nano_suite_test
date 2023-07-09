package com.test.nano_suite.dependency_injection.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nano_suite.dependency_injection.base.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(fragment: BaseFragment<*, *>) {

    private var fragment: BaseFragment<*, *>

    init {
        this.fragment = fragment
    }

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }

//    @Provides
//    fun notificationViewModel(
//        dataManager: DataManager,
//        schedulerProvider: SchedulerProvider,
//        resourceProvider: ResourceProvider
//    ): NotificationViewModel {
//        return NotificationViewModel(dataManager, schedulerProvider, resourceProvider)
//    }

}