package com.ahrgahh.timon.db;

import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";
    
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DatabaseContract() {}

    public static abstract class SMSTable implements BaseColumns {
        public static final String TABLE_NAME       	= "smsTable";
        public static final String SENDER      	   		= "sender";
        public static final String SENDER_NAME		   	= "senderName";
        public static final String TIMESTAMP_RECEIVED 	= "timestampReceived";
        public static final String TIMESTAMP_SERVICE 	= "timestampService";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                SENDER + TEXT_TYPE + COMMA_SEP +
                SENDER_NAME + TEXT_TYPE + COMMA_SEP +
                TIMESTAMP_RECEIVED + TEXT_TYPE + COMMA_SEP +
                TIMESTAMP_SERVICE + TEXT_TYPE + COMMA_SEP + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
    
    public static abstract class CallTable implements BaseColumns {
        public static final String TABLE_NAME       = "callTable";
        public static final String SENDER      	   		= "sender";
        public static final String SENDER_NAME		   	= "senderName";
        public static final String TIMESTAMP_RECEIVED 	= "timestampReceived";
        public static final String BLOCKED				= "blocked";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                SENDER + TEXT_TYPE + COMMA_SEP +
                SENDER_NAME + TEXT_TYPE + COMMA_SEP +
                TIMESTAMP_RECEIVED + TEXT_TYPE + COMMA_SEP +
                BLOCKED + TEXT_TYPE + COMMA_SEP + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
