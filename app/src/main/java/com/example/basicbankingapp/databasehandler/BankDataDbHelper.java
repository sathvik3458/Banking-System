package com.example.basicbankingapp.databasehandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.basicbankingapp.databasehandler.BankDataContract.*;


public class BankDataDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BankData.db";
    public static final int DATABASE_VERSION = 1;


    public BankDataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + users.TABLE_NAME + " ("
                + users.COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + users.COLUMN_USER_NAME + " VARCHAR, "
                + users.COLUMN_USER_EMAIL + " VARCHAR, "
                + users.COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + users.COLUMN_USER_PHONE_NO + " VARCHAR, "
                + users.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";

        String SQL_CREATE_TRANSFER_TABLE = "CREATE TABLE " + transfers.TABLE_NAME + " ("
                + transfers.COLUMN_FROM_NAME + " VARCHAR, "
                + transfers.COLUMN_TO_NAME + " VARCHAR, "
                + transfers.COLUMN_AMOUNT + " INTEGER, "
                + transfers.COLUMN_STATUS + " INTEGER, "
                + transfers.COLUMN_DATE_TIME + " VARCHAR);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_TRANSFER_TABLE);

        // Insert(DUMMY USERS) Into USERS Table
        db.execSQL("insert into " + users.TABLE_NAME + " values(78603256,'Ankush Singh', 'ankush@gmail.com','7584CID','7895641238', 15000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(58626523,'Vedant Tripathi', 'vedant@gmail.com','1258BID','8995641238', 5000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(78956589,'Kaushik Mohanty', 'kaushik@gmail.com','8896CJK','7595645896', 1000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(12589856,'Hrithik Anagire', 'hrithik@gmail.com','7752JKI','9995640038', 8000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(74104587,'Shubham More', 'shubham@gmail.com','3669POL','9095648962', 7500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(85297854,'M Sai Anirudh', 'anirudh@gmail.com','9985YJH','8855640238', 6500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(36981254,'Yash Pratap', 'yash@gmail.com','1207MPP','8895640215', 4500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(78534521,'Vishal Desai', 'vishal@gmail.com','4522MAH','9985021539', 2500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(45621452,'Manish Wani', 'manish@gmail.com','6582UPP','9309565238', 10500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(23652541,'Parth Vaidya', 'parth@gmail.com','5450BPS','8292591201', 9900)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(78544785,'Harsh Sharma', 'harsh@gmail.com','2656POI','9015641200', 9800)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(36215874,'Swapnil Chikte', 'swapnil@gmail.com','1203IPO','9995641999', 11000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(11225896,'Ashish Shirude', 'ashish@gmail.com','5566DEL','9119541001', 5800)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(95126985,'Aditya Wani', 'aditya@gmail.com','2236AUR','6254642205', 3500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(75302563,'Avinash Singh', 'avinash@gmail.com','6692SOL','6893641266', 1010)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
