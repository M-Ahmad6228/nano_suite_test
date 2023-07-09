package com.test.nano_suite.dependency_injection.base

import androidx.lifecycle.ViewModel
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator>(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    resourceProvider: ResourceProvider
) : ViewModel() {
    private var mDataManager: DataManager = dataManager

    private var mSchedulerProvider: SchedulerProvider = schedulerProvider

    private var mResourceProvider: ResourceProvider = resourceProvider

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var mNavigator: WeakReference<N>

    open fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

    open fun getDataManager(): DataManager {
        return mDataManager
    }

    open fun getNavigator(): N {
        return mNavigator.get()!!
    }

    open fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    open fun getSchedulerProvider(): SchedulerProvider {
        return mSchedulerProvider
    }
}