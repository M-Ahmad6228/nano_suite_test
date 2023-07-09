package com.test.nano_suite.dependency_injection.data

import com.test.nano_suite.dependency_injection.data.local.db.DbHelper
import com.test.nano_suite.dependency_injection.data.local.preferences.PreferencesHelper
import com.test.nano_suite.dependency_injection.network.ApiHelper

interface DataManager : DbHelper, ApiHelper, PreferencesHelper {

}