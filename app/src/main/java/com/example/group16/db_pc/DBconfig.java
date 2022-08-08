package com.example.group16.db_pc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBconfig {
    private MyDatabase mydb;
    private SQLiteDatabase database;
    private final Context context;

    public DBconfig(Context context) {
        this.context = context;
    }
    public void open() throws SQLException {
        mydb = new MyDatabase(context);
        database = mydb.getWritableDatabase();
    }

    public void close() {
        mydb.close();
    }

//insert data into db
public long insert(String cp_serial,String cp_name, String cp_ram,String size) {
    ContentValues contentValue = new ContentValues();
    contentValue.put(MyDatabase.SERIAL_NUMBER, cp_serial);
    contentValue.put(MyDatabase.COMPUTER_NAME, cp_name);
    contentValue.put(MyDatabase.COMPUTER_RAM, cp_ram);
    contentValue.put(MyDatabase.SCREEN_SIZE, size);
    return database.insert(MyDatabase.TABLE_NAME, null, contentValue);
}
    public Cursor getcomputerTypes() {
        String[] columns = new String[]{MyDatabase._ID, MyDatabase.SERIAL_NUMBER, MyDatabase.COMPUTER_NAME,MyDatabase.COMPUTER_RAM,MyDatabase.SCREEN_SIZE};
        Cursor cursor = database.query(MyDatabase.TABLE_NAME, columns, null, null, null, null,"pc_id");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
