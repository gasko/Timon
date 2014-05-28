package com.ahrgahh.timon.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {    
    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.CallTable.CREATE_TABLE);
        db.execSQL(DatabaseContract.SMSTable.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	//FIXME should be replaced so it won't drop data
        db.execSQL(DatabaseContract.CallTable.DELETE_TABLE);
        db.execSQL(DatabaseContract.SMSTable.DELETE_TABLE);
        onCreate(db);
    }
}