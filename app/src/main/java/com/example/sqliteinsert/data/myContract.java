package com.example.sqliteinsert.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by niels on 17-10-2017.
 */

public final class myContract {
//    public static final String CONTENT_AUTHORITY = "com.example.sqliteinsert";
//
//    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//
//    public static final String PATH_BEETLES = "users";

    public static abstract class myEntry implements BaseColumns {
        public static final String TABLE_NAME = "myTable";
        public static final String UID="_id";
        public static final String NAME = "Name";
        public static final String PASSWORD= "Password";
    }
}
