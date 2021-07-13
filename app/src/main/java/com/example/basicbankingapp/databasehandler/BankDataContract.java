package com.example.basicbankingapp.databasehandler;

import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentResolver;

public final class BankDataContract {
    public static final String CONTENT_AUTHORITY = "com.example.basicbankingapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_USERS = "users";
    public static final String PATH_TRANSFERS = "transfers";

    private BankDataContract() {
    }

    public static final class users implements BaseColumns {

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);

        //        Table Name
        public final static String TABLE_NAME = "users";

        //        Columns
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_USER_NAME = "name";
        public final static String COLUMN_USER_ACCOUNT_NUMBER = "account_no";
        public final static String COLUMN_USER_EMAIL = "email";
        public final static String COLUMN_USER_IFSC_CODE = "ifsc_code";
        public final static String COLUMN_USER_PHONE_NO = "mobile_no";
        public final static String COLUMN_USER_ACCOUNT_BALANCE = "current_balance";
    }

    public static final class transfers implements BaseColumns {

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRANSFERS;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRANSFERS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TRANSFERS);

        //        Table Name
        public final static String TABLE_NAME = "transfers";

        //        Columns
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FROM_NAME = "from_name";
        public final static String COLUMN_TO_NAME = "to_name";
        public final static String COLUMN_AMOUNT = "amount";
        public final static String COLUMN_STATUS = "status";
        public final static String COLUMN_DATE_TIME = "date_time";

    }
}
