package com.test.nano_suite.dependency_injection.data

import com.test.nano_suite.dependency_injection.data.local.db.DbHelper
import com.test.nano_suite.dependency_injection.data.local.preferences.PreferencesHelper
import com.test.nano_suite.dependency_injection.network.ApiHelper
import com.test.nano_suite.dependency_injection.network.AppApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    dbHelper: DbHelper,
    preferencesHelper: PreferencesHelper,
    apiHelper: AppApiHelper
) :
    DataManager {

    private val TAG = "AppDataManager"
    private val mDbHelper: DbHelper = dbHelper
    private val mApiHelper: ApiHelper = apiHelper
    private val mPreferencesHelper: PreferencesHelper = preferencesHelper

    override fun saveString(pRef: String?, value: String?) {
        mPreferencesHelper.saveString(pRef, value)
    }

    override fun getString(pRef: String?): String? {
        return mPreferencesHelper.getString(pRef)
    }

    override fun saveBoolean(pRef: String?, value: Boolean) {
        mPreferencesHelper.saveBoolean(pRef, value)
    }

    override fun getBoolean(pRef: String?): Boolean {
        return mPreferencesHelper.getBoolean(pRef)
    }

    override fun saveInt(pRef: String?, value: Int) {
        mPreferencesHelper.saveInt(pRef, value)
    }

    override fun getInt(pRef: String?): Int {
        return mPreferencesHelper.getInt(pRef)
    }

    override fun clear() {
        mPreferencesHelper.clear()
    }
}