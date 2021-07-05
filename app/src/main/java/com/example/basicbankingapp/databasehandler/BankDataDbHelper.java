package com.example.basicbankingapp.databasehandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.basicbankingapp.databasehandler.BankDataContract.*;

import androidx.annotation.Nullable;

public class BankDataDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="BankData.db";
    public static final int DATABASE_VERSION=1;


    public BankDataDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + users.TABLE_NAME + " ("
                + users.COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + users.COLUMN_USER_NAME + " VARCHAR, "
                + users.COLUMN_USER_EMAIL + " VARCHAR, "
                + users.COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + users.COLUMN_USER_PHONE_NO + " VARCHAR, "
                + users.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";

        String SQL_CREATE_TRANSFER_TABLE =  "CREATE TABLE " + transfers.TABLE_NAME + " ("
                + transfers.COLUMN_FROM_NAME + " VARCHAR, "
                + transfers.COLUMN_TO_NAME + " VARCHAR, "
                + transfers.COLUMN_AMOUNT + " INTEGER, "
                + transfers.COLUMN_STATUS + " INTEGER);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_TRANSFER_TABLE);

        // Insert(DUMMY USERS) Into USERS Table
        db.execSQL("insert into " + users.TABLE_NAME + " values(7860,'Tanishq Saini', 'tanishq@gmail.com','7584','7895641238', 15000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(5862,'Gagan Tripathi', 'gagan@gmail.com','1258','8995641238', 5000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(7895,'Surya Pratap', 'surya@gmail.com','8896','7595645896', 1000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(1258,'Vikram Garasiya', 'vikram@gmail.com','7752','9995640038', 8000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(7410,'Shivani Kumari', 'shivani@gmail.com','3669','9095648962', 7500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(8529,'Piyush Joshi', 'piyush@gmail.com','9985','8855640238', 6500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(3698,'Yash Pratap', 'yash@gmail.com','1207','8895640215', 4500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(7853,'Khushi Jain', 'khushi@gmail.com','4522','9985021539', 2500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(4562,'Ritik Sharma', 'ritik@gmail.com','6582','9309565238', 10500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(2365,'Rohit Patidar', 'rohit@gmail.com','5450','8292591201', 9900)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(7854,'Anurag Sharma', 'anurag@gmail.com','2656','9015641200', 9800)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(3621,'Hitish Kumar', 'hitish@gmail.com','1203','9995641999', 11000)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(1122,'Naveen Chaturvedi', 'naveen@gmail.com','5566','9119541001', 5800)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(9512,'Gauri Parashar', 'gauri@gmail.com','2236','6254642205', 3500)");
        db.execSQL("insert into " + users.TABLE_NAME + " values(7530,'Farhan Khan', 'farhan@gmail.com','6692','6893641266', 1010)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
