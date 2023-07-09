package com.test.nano_suite.dependency_injection.di.module

import android.app.Application
import android.content.Context
import com.test.nano_suite.dependency_injection.data.AppDataManager
import com.test.nano_suite.dependency_injection.data.DataManager
import com.test.nano_suite.dependency_injection.data.local.db.AppDbHelper
import com.test.nano_suite.dependency_injection.data.local.db.DbHelper
import com.test.nano_suite.dependency_injection.data.local.preferences.AppPreferencesHelper
import com.test.nano_suite.dependency_injection.data.local.preferences.AppPreferencesHelper.Companion.PREF_NAME
import com.test.nano_suite.dependency_injection.data.local.preferences.PreferencesHelper
import com.test.nano_suite.dependency_injection.di.scope.PreferenceInfo
import com.test.nano_suite.dependency_injection.network.ApiHelper
import com.test.nano_suite.dependency_injection.network.AppApiHelper
import com.test.nano_suite.dependency_injection.rx.AppSchedulerProvider
import com.test.nano_suite.dependency_injection.rx.ResourceProvider
import com.test.nano_suite.dependency_injection.rx.SchedulerProvider
import com.test.nano_suite.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): Database {
        return Database.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return PREF_NAME
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context);
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

}