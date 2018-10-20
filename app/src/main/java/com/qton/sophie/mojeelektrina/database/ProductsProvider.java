package com.qton.sophie.mojeelektrina.database;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.qton.sophie.mojeelektrina.database.ProductsContract.CONTENT_AUTHORITY;
import static com.qton.sophie.mojeelektrina.database.ProductsContract.PATH_ENERGY_USAGE;

public class ProductsProvider extends ContentProvider {

    public static final String MY_TAG = "xyz";
    private static final int WHOLE_TABLE = 100;
    private static final int SINGLE_USAGE = 101;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_ENERGY_USAGE, WHOLE_TABLE);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_ENERGY_USAGE + "/#", SINGLE_USAGE);
    }

    private ProductsDatabaseHelper productsDatabaseHelper;

    @Override
    public boolean onCreate() {
        productsDatabaseHelper = new ProductsDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = productsDatabaseHelper.getReadableDatabase();
        Cursor cursor;
        int uri_match = uriMatcher.match(uri);
        switch (uri_match) {
            case WHOLE_TABLE:
                cursor = database.query(ProductsContract.ProductEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SINGLE_USAGE:
                selection = ProductsContract.ProductEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ProductsContract.ProductEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                Log.e(MY_TAG, uri.toString() + " invalid uri");
                throw new IllegalArgumentException();

        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case WHOLE_TABLE:
                return ProductsContract.ProductEntry.CONTENT_LIST_TYPE;
            case SINGLE_USAGE:
                return ProductsContract.ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int uri_match = uriMatcher.match(uri);
        switch ((uri_match)) {
            case WHOLE_TABLE:
                insertProduct(uri, values);
                break;
            default:
                Log.e("xyz", uri.toString() + " invalid uri");
                throw new IllegalArgumentException("URI " + uri + " has not been recognized");
        }
        return uri;
    }

    public Uri insertProduct(Uri uri, ContentValues values) {

        /*checkProductName(values);
        checkPrice(values);
        checkQuantity(values);*/

        SQLiteDatabase database = productsDatabaseHelper.getWritableDatabase();

        long id = database.insert(ProductsContract.ProductEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(MY_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = productsDatabaseHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = uriMatcher.match(uri);
        switch (match) {
            case WHOLE_TABLE:
                rowsDeleted = database.delete(ProductsContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case SINGLE_USAGE:
                selection = ProductsContract.ProductEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(ProductsContract.ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case WHOLE_TABLE:
                return updateProduct(uri, values, selection, selectionArgs);
            case SINGLE_USAGE:
                selection = ProductsContract.ProductEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Couldn't update " + uri);
        }
    }

    private int updateProduct(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.size() == 0) {
            return 0;
        }
        SQLiteDatabase database = productsDatabaseHelper.getWritableDatabase();

        int rowsUpdated = database.update(ProductsContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
/*
    private void checkProductName(ContentValues values) {
        String name = values.getAsString(ProductsContract.ProductEntry.DATE);
        if (name == null) {
            Log.e(MY_TAG, "Product name not found");
            throw new IllegalArgumentException();
        }
    }

    private void checkPrice(ContentValues values) {
        Integer mPrice = values.getAsInteger(ProductsContract.ProductEntry.TIME);
        if (mPrice == null || mPrice < 0) {
            Log.e(MY_TAG, "Price not found or invalid");
            throw new IllegalArgumentException("Invalid price");
        }
    }

    private void checkQuantity(ContentValues values) {
        Integer mQuantity = values.getAsInteger(ProductsContract.ProductEntry.ENERGY_USAGE);
        if (mQuantity == null || mQuantity < 0) {
            Log.e(MY_TAG, "Quantity not found or invalid, setting to 0");
            throw new IllegalArgumentException();
        }
    }*/

}
