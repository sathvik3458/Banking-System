package com.example.basicbankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSingleUser extends AppCompatActivity {

    TextView name, phoneNumber, email, account_no, ifsc_code, balance;
    Button transfer_button;
    AlertDialog dialog;
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
        transfer_button = findViewById(R.id.transfer_button);

        name.setText(getIntent().getStringExtra("name"));
        phoneNumber.setText(getIntent().getStringExtra("mob_no"));
        email.setText(getIntent().getStringExtra("email"));
        account_no.setText(getIntent().getStringExtra("accountno"));
        ifsc_code.setText(getIntent().getStringExtra("ifsc"));
        balance.setText(getIntent().getStringExtra("amount"));

        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount();
            }
        });
        }
    private void enterAmount() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(ViewSingleUser.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_box, null);
        mBuilder.setTitle("Enter Amount").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);
        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                transactionCancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether amount entered is correct or not
                int currentBalance = Integer.parseInt(String.valueOf(balance.getText()));

                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount can't be empty");
                } else if (Integer.parseInt(mAmount.getText().toString()) > currentBalance){
                    mAmount.setError("Your account don't have enough balance");
                } else {
                    Intent intent = new Intent(ViewSingleUser.this, TransactionUserList.class);
                    intent.putExtra("FROM_USER_ACCOUNT_NO", Integer.parseInt(account_no.getText().toString()));    // PRIMARY_KEY
                    intent.putExtra("FROM_USER_NAME", name.getText());
                    intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.getText());
                    intent.putExtra("TRANSFER_AMOUNT", mAmount.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void transactionCancel() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(ViewSingleUser.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ViewSingleUser.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                enterAmount();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }
    }
