package com.example.basicbankingapp.databasehandler;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basicbankingapp.databasehandler.BankDataContract.*;

public class BankDataProvider extends ContentProvider {
    BankDataDbHelper myDbHelper;

    private static final int BANK = 100;
    private static final int BANK_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(BankDataContract.CONTENT_AUTHORITY, BankDataContract.PATH_USERS, BANK);

        sUriMatcher.addURI(BankDataContract.CONTENT_AUTHORITY, BankDataContract.PATH_USERS + "/#", BANK_ID);
    }

    @Override
    public boolean onCreate() {
        myDbHelper = new BankDataDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);

        if (match == BANK) {
            cursor = db.query(users.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        } else if (match == BANK_ID) {
            selection = users._ID + "=?";
            selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
            cursor = db.query(users.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        } else
            throw new IllegalArgumentException("Cannot query unknown URI " + uri);

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BANK:
                return users.CONTENT_LIST_TYPE;
            case BANK_ID:
                return users.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
