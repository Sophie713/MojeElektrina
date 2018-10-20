package com.qton.sophie.mojeelektrina.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class ProductsContract {

    private ProductsContract(){}

    public static final String CONTENT_AUTHORITY = "com.qton.sophie.mojeelektrina";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ENERGY_USAGE = "energy_usage";

    public static final class ProductEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, ProductsContract.PATH_ENERGY_USAGE);

        public static final String TABLE_NAME = "energy_usage";
        public static final String _ID = BaseColumns._ID;
        /**
         * time in millis
         */
        public static final String DATE_TIME = "date_time";
        public static final String ENERGY_USAGE = "usage";



        public static final String CONTENT_LIST_TYPE =  ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + ProductsContract.PATH_ENERGY_USAGE;;
        public static final String CONTENT_ITEM_TYPE =  ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + ProductsContract.PATH_ENERGY_USAGE;;
    }
}