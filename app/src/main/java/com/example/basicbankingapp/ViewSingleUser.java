package com.example.basicbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class ViewSingleUser extends AppCompatActivity {

    TextView name, phoneNumber, email, account_no, ifsc_code, balance;
    Button transfer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_user);

        name = findViewById(R.id.usernameVSU);
        phoneNumber = findViewById(R.id.usermobilenoVSU);
        email = findViewById(R.id.useremailidVSU);
        account_no = findViewById(R.id.useraccountnoVSU);
        ifsc_code = findViewById(R.id.userifscVSU);
        balance = findViewById(R.id.userbalanceVSU);
        transfer_button = findViewById(R.id.button);

        name.setText(getIntent().getStringExtra("name"));
        phoneNumber.setText(getIntent().getStringExtra("mob_no"));
        email.setText(getIntent().getStringExtra("email"));
        account_no.setText(getIntent().getStringExtra("accountno"));
        ifsc_code.setText(getIntent().getStringExtra("ifsc"));
        balance.setText(getIntent().getStringExtra("amount"));

        };
    }
