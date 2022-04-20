package com.example.bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Main_Activity";

    DataBase dbcon;
    RadioButton radioManager, radioClient;
    EditText UserName, LoginPassword;
    public static String accountNumberFromDataBase;
    String userFromDataBase;
    String passwordFromDataBase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dbcon = new DataBase(this);
        Log.i(TAG, "onCreate :");
        setContentView(R.layout.activity_main);

        UserName = (EditText) findViewById(R.id.txtUserName);
        LoginPassword = (EditText) findViewById(R.id.txtPassword);

        radioManager = (RadioButton) findViewById(R.id.radioManager);
        radioClient = (RadioButton) findViewById(R.id.radioClient);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        Button btnLogIn = (Button) findViewById(R.id.btnLogin);

        //Event to check the fields and after that make connection to data base to check username and password--------------------
//****************************************************************************************************************************
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean managerConditon = false;
                if (radioManager.isChecked() == false && radioClient.isChecked() == false &&
                        UserName.getText().toString().equals("") && LoginPassword.getText().toString().equals("")) {

                    Toast.makeText(MainActivity.this, "Please fill all fields!!", Toast.LENGTH_SHORT).show();

                }
                if (radioManager.isChecked() == true && UserName.getText().toString().equals("hello") && LoginPassword.getText().toString().equals("12345")) {

                    Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                    startActivity(intent);
                    Toast toast= Toast.makeText(MainActivity.this, "Welcome Manager", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                } else if (radioManager.isChecked() == false && radioClient.isChecked()) {

                    boolean ClientCondition = false;
                    SQLiteDatabase db = dbcon.getReadableDatabase();
                    Cursor c = db.rawQuery("SELECT idn,password,accnum FROM Clients", null);
                    if (c.moveToFirst()) {
                        do {
                            userFromDataBase = c.getString(0);
                            passwordFromDataBase = c.getString(1);
                            accountNumberFromDataBase = c.getString(2);

                            if (UserName.getText().toString().equals(userFromDataBase.toString())
                                    && LoginPassword.getText().toString().equals(passwordFromDataBase.toString())) {

                                ClientCondition = true;

                                TransactionsActivity.accountNumberFromData = accountNumberFromDataBase;

                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setCancelable(true);
                                builder.setTitle("AccountNumber");
                                builder.setMessage("Your Account number is :" + accountNumberFromDataBase + " \n");
                                builder.setPositiveButton(
                                        "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                builder.show();
                            }
                        } while (c.moveToNext());
                        c.moveToLast();
                        db.close();
                        c.close();

                    } else {
                        Toast.makeText(getApplicationContext(), "Not Rigestred!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//***********************************************************************************************************************************
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
//*************************************************************************************************************************************
    public static String getAccountNumberFromDataBase() {
        return accountNumberFromDataBase;
    }
//**************************************************************************************************************************************
    public static void setAccountNumberFromDataBase(String accountNumberFromDataBase) {
        MainActivity.accountNumberFromDataBase = accountNumberFromDataBase;
    }
}