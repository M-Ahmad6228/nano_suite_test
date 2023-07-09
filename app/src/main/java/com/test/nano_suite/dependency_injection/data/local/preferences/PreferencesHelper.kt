package com.test.nano_suite.dependency_injection.data.local.preferences

interface PreferencesHelper {

    fun saveString(pRef: String?, value: String?)

    fun getString(pRef: String?): String?

    fun saveBoolean(pRef: String?, value: Boolean)

    fun getBoolean(pRef: String?): Boolean

    fun saveInt(pRef: String?, value: Int)

    fun getInt(pRef: String?): Int

    fun clear()

}