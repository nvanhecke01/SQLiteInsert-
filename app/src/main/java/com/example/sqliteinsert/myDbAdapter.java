package com.example.sqliteinsert;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteinsert.data.myDbHelper;
import com.example.sqliteinsert.data.myContract.myEntry;

public class myDbAdapter  {

    myDbHelper helper;

    public myDbAdapter(Context context)
    {
        helper = new myDbHelper(context);
    }

    public long insertData(String name, String password)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myEntry.NAME, name);
        contentValues.put(myEntry.PASSWORD, password);
        long id = db.insert(myEntry.TABLE_NAME, null , contentValues);
        return id;
    }
}
