package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedger {

    public static void main(String[] args) {
    Scanner myScanner = new Scanner(System.in);
    
    // creating an empty arraylist of transaction objects
        ArrayList<Transaction> transactions = AccountingLedger.loadTransactions();
    

    }
    //
    public static ArrayList<Transaction> loadTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        return transactions;
    }

    private static void homeScreen(Scanner myScanner){

    }

}
