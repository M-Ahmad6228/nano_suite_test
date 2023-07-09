package com.test.nano_suite.dependency_injection.data.local.db

import com.test.nano_suite.database.Database
import javax.inject.Inject

class AppDbHelper @Inject constructor(database: Database) : DbHelper {

    private var mAppDatabase: Database? = database

//    override fun deleteAllContacts(): Observable<Boolean?> {
//        return Observable.fromCallable {
//            mAppDatabase!!.deleteAllContacts()
//            true
//        }
//    }
}