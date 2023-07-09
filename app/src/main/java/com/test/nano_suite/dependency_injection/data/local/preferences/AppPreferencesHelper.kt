package com.test.nano_suite.dependency_injection.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.test.nano_suite.dependency_injection.di.scope.PreferenceInfo
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Context, @PreferenceInfo prefFileName: String?) :
    PreferencesHelper {

    private val TAG = "AppPreferencesHelper"
    private var mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    companion object {
        val PREF_NAME = "user_prefs"
    }

    override fun saveString(pRef: String?, value: String?) {
        try {
            val editor = mPrefs.edit()
            editor.putString(pRef, value)
            editor.apply()
        } catch (e: Exception) {
            Log.e(TAG, "" + e.message)
        }
    }

    override fun getString(pRef: String?): String? {
        return mPrefs.getString(pRef, null)
    }

    override fun saveBoolean(pRef: String?, value: Boolean) {
        try {
            val editor = mPrefs.edit()
            editor.putBoolean(pRef, value)
            editor.apply()
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "" + e.message)
        }
    }

    override fun getBoolean(pRef: String?): Boolean {
        return mPrefs.getBoolean(pRef, false)
    }

    override fun saveInt(pRef: String?, value: Int) {
        try {
            val editor = mPrefs.edit()
            editor.putInt(pRef, value)
            editor.apply()
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "" + e.message)
        }
    }

    override fun getInt(pRef: String?): Int {
        return mPrefs.getInt(pRef, -1)
    }

    override fun clear() {
        mPrefs.edit().clear().apply()
    }
}