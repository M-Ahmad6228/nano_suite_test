package com.test.nano_suite.dependency_injection.base

interface BaseNavigator {
    fun showProgressLoader()
    fun hideProgressLoader()
    fun toast(text: String)
}