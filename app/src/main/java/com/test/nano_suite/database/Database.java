package com.test.nano_suite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DBNAME = "nano_suite.db";
    private SQLiteDatabase mDatabase;
    private static Database single_instance = null;

    private Database(Context context) {
        super(context, DBNAME, null, 1);
    }

    public static Database getInstance(Context context) {
        if (single_instance == null) single_instance = new Database(context);

        return single_instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
