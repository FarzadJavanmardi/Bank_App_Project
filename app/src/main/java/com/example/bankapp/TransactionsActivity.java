package com.example.bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionsActivity extends AppCompatActivity {


    Button btnDeposit, btnTransfer, btnWithdraw;
    static TextView lblAccountNumberTop;
    DataBase dbcon;
    EditText txtDeposit, txtAmountTransfer;
    static EditText txtWithdraw;
    static EditText txtDesAccNum;
    static String accountNumberFromData;
    static String desAccountNumber;
    static long longInitialAmountToCompare;

    Transaction trp;
    Transaction trp2;
    Transaction trp3;

    public boolean depositCondition = false;
    public boolean withdrawCondition = false;
    public boolean transferCondition = false;

    //*********************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        dbcon = new DataBase(this);

        lblAccountNumberTop = (TextView) findViewById(R.id.lblViewAccountNumber);
        lblAccountNumberTop.setText(accountNumberFromData.toString());

        btnDeposit = (Button) findViewById(R.id.btnDeposit);
        txtDeposit = (EditText) findViewById(R.id.txtDeposit);

        txtDesAccNum = (EditText) findViewById(R.id.txtDesAccNum);
        txtAmountTransfer = (EditText) findViewById(R.id.txtAmountForTransfer);
        btnTransfer = (Button) findViewById(R.id.btnTransfer);

        txtWithdraw = (EditText) findViewById(R.id.txtWithdraw);
        btnWithdraw = (Button) findViewById(R.id.btnWithdraw);

//*******************************************************************************************************************************************
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositCondition = true;
                withdrawCondition = false;
                transferCondition = false;

                //   long longTxtDeposit=Long.parseLong(txtDeposit.getText().toString());

                if (txtDeposit.getText().toString().equals("") && txtDeposit.getText().toString().isEmpty()) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill amount  to Deposit!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else {

                    trp = new Transaction();

                    trp.setSrcAccNum(TransactionsActivity.accountNumberFromData);
                    trp.setAmountForDeposit(txtDeposit.getText().toString());
                    trp.setDesAccNum(" null ");
                    trp.setType(" Deposit "+"  "+trp.getDate());
                    String depositAmount = txtDeposit.getText().toString();

                    String mes;
                    boolean insertCondition = dbcon.insertDataIntoTransactions(trp.getSrcAccNum(), trp.getDesAccNum(), trp.getAmountForDeposit(), trp.getType());

                    if (insertCondition == true)
                        mes = "";
                    else
                        mes = "";

                    SQLiteDatabase db = dbcon.getWritableDatabase();
                    Cursor c = db.rawQuery("SELECT initialamount FROM Clients WHERE accnum='" + TransactionsActivity.accountNumberFromData + "'", null);

                    while (c.moveToNext()) {

                        long Da = Long.parseLong(depositAmount);
                        long oldBlanceToLong = Long.parseLong(c.getString(0));
                        long newBalance = Da + oldBlanceToLong;
                        String newBalance2 = String.valueOf(newBalance);

                        db.execSQL("UPDATE Clients SET initialamount='" + newBalance2 + "' WHERE accnum='" + TransactionsActivity.accountNumberFromData + "' ");

                    }
                    db.close();
                    c.close();

                    Toast toast2 = Toast.makeText(TransactionsActivity.this, mes = "Success Deposit!", Toast.LENGTH_LONG);
                    toast2.setGravity(Gravity.CENTER, 0, 0);
                    toast2.show();

                }

            }
        });

        //********************************************************************************************************************************************
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdrawCondition = true;
                transferCondition = false;
                depositCondition = false;

                //    long longWithdraw=Long.parseLong(txtWithdraw.getText().toString());

                if (txtWithdraw.getText().toString().equals("")) {

                    Toast toast3 = Toast.makeText(getApplicationContext(), "Please fill amount  to Withdraw!", Toast.LENGTH_SHORT);
                    toast3.setGravity(Gravity.CENTER, 0, 0);
                    toast3.show();

                } else {

                    String AmountW = txtWithdraw.getText().toString();
                    SQLiteDatabase db3 = dbcon.getWritableDatabase();
                    Cursor c3 = db3.rawQuery("SELECT initialamount FROM Clients WHERE accnum='" + TransactionsActivity.accountNumberFromData + "'", null);

                    while (c3.moveToNext()) {

                        long wa = Long.parseLong(AmountW);
                        long oldBlanceToLong = Long.parseLong(c3.getString(0));

                        if (oldBlanceToLong > wa) {
                            long newBalance = oldBlanceToLong - wa;
                            String newBalance3 = String.valueOf(newBalance);
                            db3.execSQL("UPDATE Clients SET initialamount='" + newBalance3 + "' WHERE accnum='" + TransactionsActivity.accountNumberFromData + "' ");


                            trp2 = new Transaction();

                            trp2.setSrcAccNum(TransactionsActivity.accountNumberFromData);
                            trp2.setAmountForWithdraw(txtWithdraw.getText().toString());
                            trp2.setDesAccNum("");
                            trp2.setType(" Withdraw "+" "+trp2.getDate());


                            String mes3;
                            boolean insertCondition = dbcon.insertDataIntoTransactions(trp2.getSrcAccNum(), trp2.getDesAccNum(), trp2.getAmountForWithdraw(), trp2.getType());

                            if (insertCondition == true)
                                mes3 = "";
                            else
                                mes3 = "";


                            Toast toast4 = Toast.makeText(TransactionsActivity.this, mes3 = "Sucsess Withdraw!", Toast.LENGTH_LONG);
                            toast4.setGravity(Gravity.CENTER, 0, 0);
                            toast4.show();

                        } else {
                            Toast toast4 = Toast.makeText(TransactionsActivity.this, "No enough Balance!", Toast.LENGTH_LONG);
                            toast4.setGravity(Gravity.CENTER, 0, 0);
                            toast4.show();


                        }

                    }
                    db3.close();
                    c3.close();


                }

            }
        });

        //*************************************************************************************************************************************************

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferCondition = true;
                depositCondition = false;
                withdrawCondition = false;

                if (txtAmountTransfer.getText().toString().equals("") || txtDesAccNum.getText().toString().equals("")) {

                    Toast toast5 = Toast.makeText(getApplicationContext(), "Please fill the fields to transfer!", Toast.LENGTH_SHORT);
                    toast5.setGravity(Gravity.CENTER, 0, 0);
                    toast5.show();

                } else {


                    String amountT = txtAmountTransfer.getText().toString();
                    desAccountNumber = TransactionsActivity.txtDesAccNum.getText().toString();


                    SQLiteDatabase db4 = dbcon.getWritableDatabase();

                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Cursor c4 = db4.rawQuery("SELECT initialamount FROM Clients WHERE accnum='" + TransactionsActivity.accountNumberFromData + "'", null);

                    while (c4.moveToNext()) {
                        long ta2 = Long.parseLong(amountT);
                        long oldBlanceToLong2 = Long.parseLong(c4.getString(0));

                        if (oldBlanceToLong2 >= ta2) {
                            long newBalance3 = oldBlanceToLong2 - ta2;
                            String newBalance4 = String.valueOf(newBalance3);


                            db4.execSQL("UPDATE Clients SET initialamount='" + newBalance4 + "' WHERE accnum='" + TransactionsActivity.accountNumberFromData + "' ");


                            c4 = db4.rawQuery("SELECT initialamount FROM Clients WHERE accnum='" + desAccountNumber + "'", null);

                            while (c4.moveToNext()) {

                                long ta = Long.parseLong(amountT);
                                long oldBlanceToLong = Long.parseLong(c4.getString(0));
                                long newBalance = ta + oldBlanceToLong;
                                String newBalance2 = String.valueOf(newBalance);

                                db4.execSQL("UPDATE Clients SET initialamount='" + newBalance2 + "' WHERE accnum='" + desAccountNumber + "' ");

                                trp3 = new Transaction();

                                trp3.setSrcAccNum(TransactionsActivity.accountNumberFromData);
                                trp3.setAmountForTransfer(txtAmountTransfer.getText().toString());
                                trp3.setDesAccNum(TransactionsActivity.txtDesAccNum.getText().toString());
                                trp3.setType(" Transfer "+" "+trp3.getDate());
                                String mes;
                                boolean insertCondition = dbcon.insertDataIntoTransactions(trp3.getSrcAccNum(), trp3.getDesAccNum(), trp3.getAmountForTransfer(), trp3.getType());

                                if (insertCondition == true)
                                    mes = "";
                                else
                                    mes = "";




                                Toast toast2 = Toast.makeText(TransactionsActivity.this, mes = "Success transfer!", Toast.LENGTH_LONG);
                                toast2.setGravity(Gravity.CENTER, 0, 0);
                                toast2.show();

                            }
                            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        } else {

                            Toast toast2 = Toast.makeText(TransactionsActivity.this,"No enough Balance!", Toast.LENGTH_LONG);
                            toast2.setGravity(Gravity.CENTER, 0, 0);
                            toast2.show();
                        }
                        db4.close();
                        c4.close();


                    }
                }


            }
        });
    }

}