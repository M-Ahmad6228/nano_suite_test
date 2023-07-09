package com.test.nano_suite.dependency_injection.di.component

import android.app.Application
import com.test.nano_suite.dependency_injection.di.module.AppModule
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import com.test.nano_suite.NanoSuiteApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: NanoSuiteApplication)

    fun getDataManager(): DataManager

    fun getSchedulerProvider(): SchedulerProvider

    fun getResourceProvider(): ResourceProvider

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}