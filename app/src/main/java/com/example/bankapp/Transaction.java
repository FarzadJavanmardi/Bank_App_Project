package com.example.bankapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Transaction {

    private String srcAccNum;
    public static String desAccNum;
    private String amountForDeposit;
    private String amountForTransfer;
    private String amountForWithdraw;
    private String type;
    DataBase dbcon;
    private String date=new Date().toString();

   //Getters and Setters..........................................................................

  //  public void setDate( date){
  //      this.date=date;
   // }

    public String getDate(){
        return date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmountForWithdraw() {
        return amountForWithdraw;
    }

    public void setAmountForWithdraw(String amountForWithdraw) {
        this.amountForWithdraw = amountForWithdraw;
    }

    public String getSrcAccNum() {
        return srcAccNum;
    }

    public void setSrcAccNum(String srcAccNum) {
        this.srcAccNum = srcAccNum;
    }

    public String getDesAccNum() {
        return desAccNum;
    }

    public void setDesAccNum(String desAccNum) {
        this.desAccNum = desAccNum;
    }

    public String getAmountForDeposit() {
        return amountForDeposit;
    }

    public void setAmountForDeposit(String amountForDeposit) {
        this.amountForDeposit = amountForDeposit;
    }

    public String getAmountForTransfer() {
        return amountForTransfer;
    }

    public void setAmountForTransfer(String amountForTransfer) {
        this.amountForTransfer = amountForTransfer;
    }



}
