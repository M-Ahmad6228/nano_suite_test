package com.test.nano_suite.dependency_injection.di.module

import com.test.nano_suite.dependency_injection.base.BaseDialog
import dagger.Module

@Module
class DialogModule(dialog: BaseDialog?) {

    private var dialog: BaseDialog? = null

    init {
        this.dialog = dialog
    }

//    @Provides
//    fun provideRateUsViewModel(dataManager: DataManager?, schedulerProvider: SchedulerProvider?): RateUsViewModel? {
//        val supplier: Supplier<RateUsViewModel> = Supplier<RateUsViewModel> { RateUsViewModel(dataManager, schedulerProvider) }
//        val factory: ViewModelProviderFactory<RateUsViewModel> = ViewModelProviderFactory<T>(RateUsViewModel::class.java, supplier)
//        return ViewModelProvider(dialog!!.activity!!, factory).get(RateUsViewModel::class.java)
//    }

}