package com.qton.sophie.mojeelektrina.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usage.db";
    private static final int DATABASE_VERSION = 1;

    public ProductsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + ProductsContract.ProductEntry.TABLE_NAME + " (" +
                ProductsContract.ProductEntry._ID + " INTEGER primary key autoincrement, " +
                ProductsContract.ProductEntry.DATE_TIME + " INTEGER not null, " +
                ProductsContract.ProductEntry.ENERGY_USAGE + " REAL default 0);";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProductsContract.ProductEntry.TABLE_NAME);
        onCreate(db);

    }
}
