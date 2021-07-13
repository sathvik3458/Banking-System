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

    private static final int BANK_USERS = 100;
    private static final int BANK_ID_USERS = 101;
    private static final int BANK_TRANSFER = 102;
    private static final int BANK_ID_TRANSFER = 103;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(BankDataContract.CONTENT_AUTHORITY, BankDataContract.PATH_USERS, BANK_USERS);

        sUriMatcher.addURI(BankDataContract.CONTENT_AUTHORITY, BankDataContract.PATH_USERS + "/#", BANK_ID_USERS);

        sUriMatcher.addURI(BankDataContract.CONTENT_AUTHORITY, BankDataContract.PATH_TRANSFERS, BANK_TRANSFER);

        sUriMatcher.addURI(BankDataContract.CONTENT_AUTHORITY, BankDataContract.PATH_TRANSFERS + "/#", BANK_ID_TRANSFER);
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

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case BANK_USERS:
                cursor = db.query(users.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case BANK_ID_USERS:
                selection = users._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(users.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case BANK_TRANSFER:
                cursor = db.query(transfers.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case BANK_ID_TRANSFER:
                selection = transfers._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(transfers.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BANK_USERS:
                return users.CONTENT_LIST_TYPE;
            case BANK_ID_USERS:
                return users.CONTENT_ITEM_TYPE;
            case BANK_TRANSFER:
                return transfers.CONTENT_LIST_TYPE;
            case BANK_ID_TRANSFER:
                return transfers.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        if (match == BANK_TRANSFER) {
            long id = db.insert(transfers.TABLE_NAME, null, values);
            return id == -1 ? null : ContentUris.withAppendedId(uri, id);
        }
        throw new IllegalArgumentException("Insertion id not supported for " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BANK_USERS:
                int result = db.update(users.TABLE_NAME, values, selection, selectionArgs);
                return result;
            case BANK_ID_USERS:
                selection = transfers._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int result1 = db.update(users.TABLE_NAME, values, selection, selectionArgs);
                return result1;
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
}
