package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.basicbankingapp.adapter.RecyclerViewAdapter_b;
import com.example.basicbankingapp.databasehandler.BankDataContract;
import com.example.basicbankingapp.databasehandler.BankDataContract.*;
import com.example.basicbankingapp.model.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionUserList extends AppCompatActivity implements RecyclerItemClickListener {
    RecyclerView recycler_view_b;
    RecyclerViewAdapter_b recyclerViewAdapter_b;
    ArrayList<Users> dataHolder_b = new ArrayList<Users>(); // java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.util.ArrayList.add(java.lang.Object)' on a null object reference
    Cursor cursor;


    int fromUserAccountNo, toUserAccountNo, toUserAccountBalance;
    String fromUserAccountName, fromUserAccountBalance, transferAmount, toUserAccountName;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
    String date_and_time = simpleDateFormat.format(calendar.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_user_list);

//      RecyclerView
        recycler_view_b = findViewById(R.id.recycler_view_b);
        recycler_view_b.setLayoutManager(new LinearLayoutManager(this));
        displayDatabaseInfo();
        recyclerViewAdapter_b = new RecyclerViewAdapter_b(this, dataHolder_b, this);
        recycler_view_b.setAdapter(recyclerViewAdapter_b);

//      Get time instance
//        Calendar calendar = Calendar.getInstance();
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
//        date_and_time = simpleDateFormat.format(calendar.getTime());

//      Get Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromUserAccountName = bundle.getString("FROM_USER_NAME");
            fromUserAccountNo = bundle.getInt("FROM_USER_ACCOUNT_NO");
            fromUserAccountBalance = bundle.getString("FROM_USER_ACCOUNT_BALANCE");
            transferAmount = bundle.getString("TRANSFER_AMOUNT");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        String[] projection = {
                BankDataContract.users.COLUMN_USER_NAME,
                BankDataContract.users.COLUMN_USER_PHONE_NO,
                BankDataContract.users.COLUMN_USER_ACCOUNT_BALANCE,
                BankDataContract.users.COLUMN_USER_ACCOUNT_NUMBER,
                BankDataContract.users.COLUMN_USER_IFSC_CODE,
                BankDataContract.users.COLUMN_USER_EMAIL

        };

        String sortOrder = BankDataContract.users.COLUMN_USER_NAME + " ASC";

        cursor = getContentResolver().query(BankDataContract.users.CONTENT_URI, projection, null, null, sortOrder);

        while (cursor.moveToNext()) {

            Users obj = new Users(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            dataHolder_b.add(obj);

        }

    }

    @Override
    public void onClick(int position) {
        toUserAccountName = dataHolder_b.get(position).getName();
        updateBalance(position);
        insertTransaction(position);
        Toast.makeText(this, "Transaction Successful!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void insertTransaction(int position) {
        ContentValues values = new ContentValues();
        values.put(transfers.COLUMN_FROM_NAME, fromUserAccountName);
        values.put(transfers.COLUMN_TO_NAME, toUserAccountName);
        values.put(transfers.COLUMN_STATUS, 1);
        values.put(transfers.COLUMN_AMOUNT, transferAmount);
        values.put(transfers.COLUMN_DATE_TIME, date_and_time);
        Uri newUri = getContentResolver().insert(transfers.CONTENT_URI, values);
    }

    private void updateBalance(int position) {
        toUserAccountNo = Integer.parseInt(dataHolder_b.get(position).getAccountno());
        toUserAccountBalance = Integer.parseInt(dataHolder_b.get(position).getAmount());
        int fromUser_Updated_Bal = Integer.parseInt(fromUserAccountBalance) - Integer.parseInt(transferAmount);
        int toUser_Updated_Bal = toUserAccountBalance + Integer.parseInt(transferAmount);

//        Updating Account Balance of users in 'users' table
        ContentValues values = new ContentValues();
        values.put(users.COLUMN_USER_ACCOUNT_BALANCE, fromUser_Updated_Bal);
        String selection = users.COLUMN_USER_ACCOUNT_NUMBER + "=?";
        String[] selectionArgs = {String.valueOf(fromUserAccountNo)};
        int result = getContentResolver().update(users.CONTENT_URI, values, selection, selectionArgs);

        ContentValues values1 = new ContentValues();
        values1.put(users.COLUMN_USER_ACCOUNT_BALANCE, toUser_Updated_Bal);
        String selection1 = users.COLUMN_USER_ACCOUNT_NUMBER + "=?";
        String[] selectionArgs1 = {String.valueOf(toUserAccountNo)};
        int result1 = getContentResolver().update(users.CONTENT_URI, values1, selection1, selectionArgs1);

        Log.d("Updated Row", String.valueOf(result+result1));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder_cancel = new AlertDialog.Builder(TransactionUserList.this);
        builder_cancel.setTitle("Do you want to cancel the transaction?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//          Inserting Cancelled Transaction Information in Transfer Table
                ContentValues values = new ContentValues();
                values.put(transfers.COLUMN_FROM_NAME, fromUserAccountName);
                values.put(transfers.COLUMN_TO_NAME, "N/A");
                values.put(transfers.COLUMN_STATUS, 0);
                values.put(transfers.COLUMN_AMOUNT, transferAmount);
                values.put(transfers.COLUMN_DATE_TIME, date_and_time);
                Uri newUri = getContentResolver().insert(transfers.CONTENT_URI, values);

                Toast.makeText(TransactionUserList.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
//              startActivity(new Intent(TransactionUserList.this, ViewAllCustomers.class));
                finish();
            }
        }).setNegativeButton("No", null);
        AlertDialog alertExit = builder_cancel.create();
        alertExit.show();
    }
}