package com.example.sqliteinsert.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqliteinsert.Message;
import com.example.sqliteinsert.data.myContract.myEntry;

/**
 * Created by niels on 17-10-2017.
 */

public class myDbHelper extends SQLiteOpenHelper {
    // private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private Context context;

    private static final String DATABASE_NAME = "myDatabase";

    private static final int DATABASE_Version = 1;

    myEntry myEntry;

    /**
     * Constructs a new instance of {@link myDbHelper}.
     *
     * @param context of the app
     */
    public myDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
        Message.message(context,"Started...");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String CREATE_TABLE = "CREATE TABLE "+ myEntry.TABLE_NAME + " (" +
                    myEntry.UID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    myEntry.NAME + " TEXT NOT NULL, " +
                    myEntry.PASSWORD + " TEXT NOT NULL);";
            db.execSQL(CREATE_TABLE);
            Message.message(context,"TABLE CREATED");
        } catch (Exception e) {
            Message.message(context,"" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      /*try {
           Message.message(context,"OnUpgrade");
           db.execSQL(DROP_TABLE);
           onCreate(db);
        }catch (Exception e) {
           Message.message(context,""+e);
        }*/
    }
}
