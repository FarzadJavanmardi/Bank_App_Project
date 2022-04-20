package com.example.bankapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    DataBase db;
    EditText txtName, txTLastName, txtID, txtPhoneNumber, txtMakePassword, txtInitialAmount;
    Button btnJoin,btnVerify;
    String mes = "";
//*************************************************************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DataBase(this);

        txtName = (EditText) findViewById(R.id.txtName);
        txTLastName = (EditText) findViewById(R.id.txtLastName);
        txtID = (EditText) findViewById(R.id.txtNID);
        txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);
        txtMakePassword = (EditText) findViewById(R.id.txtMakePassword);
        txtInitialAmount = (EditText) findViewById(R.id.txtInitialAmount);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnVerify = (Button) findViewById(R.id.btnVerify);


 //**************************************************************************************************************************************

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtName.getText() == null ||
                        txTLastName.getText().toString().compareTo("") == 0 ||
                        txtID.getText().toString().compareTo("") == 0 ||
                        txtPhoneNumber.getText().toString().compareTo("") == 0 ||
                        txtMakePassword.getText().toString().compareTo("") == 0 ||
                        txtInitialAmount.getText().toString().compareTo("") == 0
                ) {
                    Toast toast = Toast.makeText(RegistrationActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {

                    Person person = new Person();

                    person.setName(txtName.getText().toString());
                    person.setLastName(txTLastName.getText().toString());
                    person.setID(txtID.getText().toString());
                    person.setPhoneNumber(txtPhoneNumber.getText().toString());
                    person.setPassword(txtMakePassword.getText().toString());
                    person.setInitialAmount(txtInitialAmount.getText().toString());
                    person.setDate();
                    String number = person.getAccountNumber();

                    boolean insertCondition = db.insertData(person.getName(), person.getLastName(), person.getID(), person.getPhoneNumber(), person.getPassword(), person.getInitialAmount(), number.toString(),person.getDate());
                    if (insertCondition == true) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Id and Password");
                        builder.setMessage("Your Username is :" + person.getID() + "\n" + " and your password is : " + person.getPassword());
                        builder.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        builder.show();
                    } else {
                        mes = "Registration Faild!";
                    }

                }

            }
        });


 //*********************************************************************************************************************************************
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}