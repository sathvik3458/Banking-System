package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.basicbankingapp.adapter.RecyclerViewAdapter_c;
import com.example.basicbankingapp.databasehandler.BankDataContract.*;
import com.example.basicbankingapp.model.Transaction_History_Model;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {
    RecyclerView recycler_view_c;
    RecyclerViewAdapter_c recyclerViewAdapter_c;
    ArrayList<Transaction_History_Model> dataHolder_c = new ArrayList<Transaction_History_Model>(); // java.lang.NullPointerException: Attempt to invoke virtual method 'boolean java.util.ArrayList.add(java.lang.Object)' on a null object reference
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recycler_view_c = findViewById(R.id.recycler_view_c);
        recycler_view_c.setLayoutManager(new LinearLayoutManager(this));
        displayDatabaseInfo();
        recyclerViewAdapter_c = new RecyclerViewAdapter_c(this, dataHolder_c);
        recycler_view_c.setAdapter(recyclerViewAdapter_c);

    }

    private void displayDatabaseInfo() {
        String[] projection = {
                transfers.COLUMN_FROM_NAME,
                transfers.COLUMN_TO_NAME,
                transfers.COLUMN_AMOUNT,
                transfers.COLUMN_STATUS,
                transfers.COLUMN_DATE_TIME,
        };

//        String sortOrder = transfers._ID+ " ASC";

        cursor = getContentResolver().query(transfers.CONTENT_URI, projection, null, null, null);

        while (cursor.moveToNext()) {

            Transaction_History_Model obj = new Transaction_History_Model(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
            dataHolder_c.add(obj);

        }
    }
}