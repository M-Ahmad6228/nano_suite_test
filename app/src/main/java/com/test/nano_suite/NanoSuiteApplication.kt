package com.test.nano_suite

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.test.nano_suite.dependency_injection.di.component.AppComponent
import com.test.nano_suite.dependency_injection.di.component.DaggerAppComponent

class NanoSuiteApplication : Application() {
    companion object {
        val TAG = NanoSuiteApplication::class.java.simpleName
        lateinit var context: Context
    }

    var appComponent: AppComponent? = null
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        MultiDex.install(this)
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent!!.inject(this)
    }
}