package com.example.group16.db_pc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase  extends SQLiteOpenHelper {
    static final String TABLE_NAME = "tbl_computer";
    static final String _ID = "pc_id";
    static final String SERIAL_NUMBER = "serial_number";
    static final String COMPUTER_NAME = "computer_name";
    static final String COMPUTER_RAM = "computer_ram";
    static final String SCREEN_SIZE = "screen_size";
    static final String ADDED_ON = "added_on";
    private static final String DB_NAME = "DB_COMPUTERS";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SERIAL_NUMBER + " TEXT NOT NULL, " + COMPUTER_NAME + " TEXT NOT NULL, "
            + COMPUTER_RAM + " TEXT DEFAULT NULL, " + SCREEN_SIZE + " TEXT NOT NULL, " + ADDED_ON +
            " TEXT DEFAULT CURRENT_TIMESTAMP);";

    MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {
        sqldb.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int oldVersion, int newVersion) {
        sqldb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqldb);

    }
}
