package com.example.bankapp;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class DataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "BankAppData.db";
    public static final String TBL_NAME = "Clients";
    public static final String TBL_NAME2 = "Transactions";
    SQLiteDatabase db = this.getReadableDatabase();
//**************************************************************************************************


    public DataBase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

//**************************************************************************************************
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TBL_NAME + "(Id INTEGER PRIMARY KEY AutoIncrement , name TEXT , lastname TEXT , idn TEXT , phonenumber TEXT , password TEXT ,initialamount TEXT, accnum TEXT,date TEXT )");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TBL_NAME2 + "(source_accountnum TEXT,Destination_accountnum TEXT,amount TEXT,type_and_date TEXT)");
    }
//**************************************************************************************************
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }
//**************************************************************************************************
    public boolean insertData(String name1, String lastname1, String idn1, String phone1, String pass1, String initialamount1, String accnum1,String date1) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name1);
        cv.put("lastname", lastname1);
        cv.put("idn", idn1);
        cv.put("phonenumber", phone1);
        cv.put("password", pass1);
        cv.put("initialamount", initialamount1);
        cv.put("accnum", accnum1);
        cv.put("date",date1);

        long result = db.insert(TBL_NAME, null, cv);

        if (result == -1)
            return false;
        else
            return true;

    }

//**************************************************************************************************
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c2 = db.rawQuery("SELECT * FROM Clients", null);
        return c2;
    }
//**************************************************************************************************
    public Cursor getDataFromTransactions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c5 = db.rawQuery("SELECT * FROM Transactions", null);
        return c5;
    }

//**************************************************************************************************

    public boolean insertDataIntoTransactions(String srcaccnum1, String desacccnum1, String amount1, String typeanddate1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("source_accountnum", srcaccnum1);
        cv.put("Destination_accountnum", desacccnum1);
        cv.put("amount", amount1);
        cv.put("type_and_date", typeanddate1);

        long result = db.insert(TBL_NAME2, null, cv);

        if (result == -1)
            return false;
        else
            return true;
    }


}











