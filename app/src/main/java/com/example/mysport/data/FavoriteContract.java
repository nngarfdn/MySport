package com.example.mysport.data;

import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class FavoriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_AKTIVITASID = "aktivitasid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PHOTO = "photo";
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_FAVSTATUS = "status";
    }
}
