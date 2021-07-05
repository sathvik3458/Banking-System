package com.example.basicbankingapp.databasehandler;

import android.provider.BaseColumns;

public final class BankDataContract {
    private BankDataContract(){}
    public static final class users implements BaseColumns{

//        Table Name
        public final static String TABLE_NAME = "users";

//        Columns
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_USER_NAME ="name";
        public final static String COLUMN_USER_ACCOUNT_NUMBER ="account_no";
        public final static String COLUMN_USER_EMAIL ="email";
        public final static String COLUMN_USER_IFSC_CODE ="ifsc_code";
        public final static String COLUMN_USER_PHONE_NO ="mobile_no";
        public final static String COLUMN_USER_ACCOUNT_BALANCE ="current_balance";
    }

    public static final class transfers implements BaseColumns{

        //        Table Name
        public final static String TABLE_NAME = "transfers";

        //        Columns
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FROM_NAME = "from_name";
        public final static String COLUMN_TO_NAME = "to_name";
        public final static String COLUMN_AMOUNT = "amount";
        public final static String COLUMN_STATUS = "status";
    }
}
