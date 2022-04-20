package com.example.bankapp;

import java.util.Date;
import java.util.Random;
import java.util.Random;

public class Person {


    private String name;
    private String lastName;
    private String ID;
    private String phoneNumber;
    private String password;
    private String initialAmount;
    private String accountNumber = createAccountNumber();
    private String date;

    //getters.......................................................................................

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setDate(){
        date=new Date().toString();
    }
   public String getDate() {
       return date;
   }

    public String getLastName() {
        return lastName;
    }

    public String getID() {
        return ID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getInitialAmount() {
        return initialAmount;
    }

    //setters.......................................................................................

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInitialAmount(String initialAmount) {
        this.initialAmount = initialAmount;
    }


  //************************************************************************************************
    public static String createAccountNumber() {
        Random rand = new Random();
        int accNum = rand.nextInt(1000 + 1 + 2000);
        String strAccountNumber = String.valueOf(accNum);
        return strAccountNumber;
    }


}
