package com.pluralsight;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedger {

    public static void main(String[] args) {
    Scanner myScanner = new Scanner(System.in);
    
    // creating an empty arraylist of transaction objects
        ArrayList<Transaction> transactions = AccountingLedger.loadTransactions();
        String fileName = "src/main/resources/inventory.csv";



    }
    //
    public static ArrayList<Transaction> loadTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        return transactions;
    }

    private static void homeScreen(Scanner myScanner, ArrayList<Transaction> transactions){
        String menuPrompt = """
                Welcome to Hub's Accounting Ledger! What would you like to do today?
                D) Add Deposit
                P) Make a Payment
                L) Ledger
                x) Exit the application
                """;
        boolean exit = false;

        do{
            System.out.println(menuPrompt);
            String userInput = myScanner.nextLine();

            switch (userInput){
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> displayLedger();
                case "X" -> exit = true;
                default -> System.out.println("Please choose one of the options above");
            }
        }
        while (!exit);
    }

    private static void displayLedger() {
    }

    private static void makePayment() {
    }

    private static void addDeposit() {
    }

}
