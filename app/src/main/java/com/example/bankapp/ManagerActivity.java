package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerActivity extends AppCompatActivity {

    Button btnClientInfo;
    TextView txtInfo;
    Button btnAllTransactions;
    DataBase dbcon;
//*************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        dbcon = new DataBase(this);

        btnClientInfo = (Button) findViewById(R.id.btnClientsInfoOfManager);
        txtInfo = (TextView) findViewById(R.id.lblOfManagerPage);
        btnAllTransactions = (Button) findViewById(R.id.btnAllTransactionsManager);

 //************************************************************************************************************************************

        btnClientInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = dbcon.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Entry Exist!", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append(res.getString(0) + "\t" + ": ");
                    buffer.append("Name:" + res.getString(1) + "\t" + " ");
                    buffer.append("Lastname:" + res.getString(2) + "\n");
                    buffer.append("ID number:" + res.getString(3) + "\t" + " ");
                    buffer.append("Cellphone:" + res.getString(4) + "\n" + " ");
                    buffer.append("Password:" + res.getString(5) + "\n" + " ");
                    buffer.append("Initial Amount:" + res.getString(6) + "\t" + " ");
                    buffer.append("Account Number:" + res.getString(7) + "\n");
                    buffer.append(res.getString(8)+"\n"+"\n"+"\n");

                }
                txtInfo.setText(buffer.toString());

            }
        });

//*******************************************************************************************************************************************

        btnAllTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = dbcon.getDataFromTransactions();
                if (res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Entry Exist!", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Source account number :" + res.getString(0) + "\n");
                    buffer.append("Destination account number:" + res.getString(1) + "\n" + " ");
                    buffer.append("Amont:" + res.getString(2) + "\n");
                    buffer.append("Type and date:" + res.getString(3) + "\n" + "\n" + "\n");

                }
                txtInfo.setText(buffer.toString());

            }
        });

    }

}