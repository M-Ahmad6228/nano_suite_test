package com.test.nano_suite.dependency_injection.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nano_suite.dependency_injection.base.BaseFragment
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import com.test.nano_suite.fragments.home.HomeViewModel
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

    @Provides
    fun homeViewModel(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider,
        resourceProvider: ResourceProvider
    ): HomeViewModel {
        return HomeViewModel(dataManager, schedulerProvider, resourceProvider)
    }

}