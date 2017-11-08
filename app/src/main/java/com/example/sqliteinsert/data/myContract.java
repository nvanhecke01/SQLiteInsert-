package com.example.sqliteinsert.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by niels on 17-10-2017.
 */

public final class myContract {
    public static final String CONTENT_AUTHORITY = "com.example.sqliteinsert";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_USERS = "users";

    public static abstract class myEntry implements BaseColumns {
        public static final Uri CONTENT_URI_USER = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);

        /**
         * The MIME type of the {@link #CONTENT_URI_USER} for a list of user.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        /**
         * The MIME type of the {@link #CONTENT_URI_USER} for a single user.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public static final String TABLE_NAME = "myTable";
        public static final String UID = "_id";
        public static final String NAME = "Name";
        public static final String PASSWORD = "Password";
    }
}
