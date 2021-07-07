package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.basicbankingapp.adapter.RecyclerViewAdapter;
import com.example.basicbankingapp.databasehandler.BankDataContract.*;
import com.example.basicbankingapp.model.Users;

import java.util.ArrayList;

public class ViewAllCustomers extends AppCompatActivity {
    RecyclerView recycler_view;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Users> dataHolder = new ArrayList<Users>(); // java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.util.ArrayList.add(java.lang.Object)' on a null object reference
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);


        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        displayDatabaseInfo();
        recyclerViewAdapter = new RecyclerViewAdapter(this, dataHolder);
        recycler_view.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        String[] projection = {
                users.COLUMN_USER_NAME,
                users.COLUMN_USER_PHONE_NO,
                users.COLUMN_USER_ACCOUNT_BALANCE,
                users.COLUMN_USER_ACCOUNT_NUMBER,
                users.COLUMN_USER_IFSC_CODE,
                users.COLUMN_USER_EMAIL

        };

        String sortOrder = users.COLUMN_USER_NAME + " ASC";

        cursor = getContentResolver().query(users.CONTENT_URI, projection, null, null, sortOrder);

        while (cursor.moveToNext()) {

            Users obj = new Users(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            dataHolder.add(obj);

        }

    }

}