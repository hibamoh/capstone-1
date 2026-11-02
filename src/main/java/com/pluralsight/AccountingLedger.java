package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountingLedger {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String fileName = "src/main/resources/transactions.csv";
        ArrayList<Transaction> transactions = AccountingLedger.loadTransactions(fileName);
        homeScreen(transactions, myScanner);
        System.out.println("Welcome to Hub's Accounting Ledger!");

    }
    public static ArrayList<Transaction> loadTransactions(String fileName){
        ArrayList<Transaction> transactions = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();


        } catch (FileNotFoundException e) {
            System.out.println("I am sorry, it seems like that file does not exist");

        } catch (IOException e) {
            System.out.println("There seems to be an issue, try again");;
        }


        return transactions;
    }

    private static void homeScreen(ArrayList<Transaction> transactions, Scanner myScanner){
        String menuPrompt = """
                What would you like to do today?
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

    private static Transaction parseTransaction(String line) {
        String[] lineParts = line.split(Pattern.quote("|"));
        LocalDate date = LocalDate.parse(lineParts[0]);
        LocalTime time = LocalTime.parse(lineParts[1]);
        String description = lineParts[2];
        String vendor = lineParts[3];
        double amount = Double.parseDouble(lineParts[4]);

        Transaction transaction = new Transaction(date,time,description,vendor,amount);
        return transaction;
    }

}
